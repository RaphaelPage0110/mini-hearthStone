import { Component } from '@angular/core';
import {Stomp} from'@stomp/stompjs';
import * as SockJS from 'sockjs-client';
import {GameComponent} from "./game/game.component";
import {Mana} from "./mana.model";

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})
export class AppComponent {
    title = 'mini Hearthstone';
    description = 'Angular-WebSocket Demo';

    game : GameComponent = new GameComponent();
    greetings: string[] = [];
    myMinions: any[] = [];
    hisMinions: any[] = [];
    myCards: any[] = [];
    myMana: Mana;
    hisMana: Mana;
    hisHand : any[] = [];
    myHero : any[] = [];
    hisHero : any[] = [];
    disabled = true;
    selectedHero : string = 'Jaina';
    private stompClient = null;

    constructor() {
    }

    setConnected(connected: boolean) {
        this.disabled = !connected;

        if (connected) {
            this.greetings = [];
        }
    }

    connect() {
        const socket = new SockJS('http://localhost:8080/endpoint');
        this.stompClient = Stomp.over(socket);

        const _this = this;
        this.stompClient.connect({}, function (frame) {
            _this.setConnected(true);
            console.log('Connected: ' + frame);
            _this.showGreeting("Vous êtes bien connecté au serveur du mini-hearthstone");

            _this.stompClient.subscribe('/user/queue/reply', function (resp) {
                console.log("server answer: "+resp.body)
                _this.showGreeting(JSON.parse(resp.body).greeting);
            });

            _this.stompClient.subscribe('/user/queue/reply_myHand', function (resp) {
                console.log("server answer: "+resp.body)
                _this.showHand(resp.body);
            });

            _this.stompClient.subscribe('/user/queue/reply_gameFound', function (resp) {
                console.log("server answer: "+resp.body)
                _this.gameReady();
                _this.showGreeting(JSON.parse(resp.body).greeting);
            });

            _this.stompClient.subscribe('/user/queue/reply_hisHand', function (resp) {
                console.log("server answer: "+resp.body)
                _this.showHisHand(JSON.parse(resp.body).nbrCards);
            });

            _this.stompClient.subscribe('/user/queue/reply_myHero', function (resp) {
                console.log("server answer: "+resp.body)
                _this.showMyHero(resp.body);
            });

            _this.stompClient.subscribe('/user/queue/reply_hisHero', function (resp) {
                console.log("server answer: "+resp.body)
                _this.showHisHero(resp.body);
            });

            _this.stompClient.subscribe('/user/queue/reply_myMana', function (resp) {
                console.log("server answer: "+resp.body)
                _this.showMyMana(resp.body);
            });

            _this.stompClient.subscribe('/user/queue/reply_hisMana', function (resp) {
                console.log("server answer: "+resp.body)
                _this.showHisMana(resp.body);
            });

            _this.stompClient.subscribe('/user/queue/reply_yourTurn', function (resp) {
                console.log("server answer: "+resp.body)
                _this.openYourTurnPopup();
            });

            _this.stompClient.subscribe('/user/queue/reply_passedTurn', function (resp) {
                console.log("server answer: "+resp.body)
                _this.passedTurn();
            });

            _this.stompClient.subscribe('/user/queue/reply_gameOver', function (resp) {
                console.log("server answer: "+resp.body)
                _this.gameOver(resp.body);
            });

            _this.stompClient.subscribe('/user/queue/reply_playMinion', function (resp) {
                console.log("server answer: "+resp.body)
                _this.showMinions(resp.body);
            });

            _this.stompClient.subscribe('/user/queue/reply_playedMinion', function (resp) {
                console.log("Votre adversaire a joué: "+resp.body)
                _this.showHisMinions(resp.body);
            });



        });
    }

    gameReady() {
        document.getElementById("cancelSearch").style.display = "none";
        document.getElementById("theBoard").style.display = "block";
    }

    passTurn(){
        this.stompClient.send(
            '/passTurn',
            {}
        )
    }

    giveUp(){
        this.stompClient.send(
            '/gameOver',
            {}
        )
        this.passTurn();
    }

    gameOver(resp) {
        document.getElementById("yourTurnPop").style.display = "block";
        document.getElementById("yourTurnPopMessage").innerHTML = "<h4><b>"+resp+"</b></h4>";
        document.getElementById("theBoard").style.display = "none";
        document.getElementById("connectToGame").style.display = "block";
        this.myCards = [];
        this.hisHand = [];
        this.myHero = [];
        this.hisHero = [];
        this.myMinions = [];
        this.hisMinions = [];
    }

    openYourTurnPopup() {
        document.getElementById("yourTurnPop").style.display = "block";
        document.getElementById("yourTurnPopMessage").innerHTML = "<h4><b>C'est à vous!</b></h4>";
    }

    passedTurn(){
        document.getElementById("passTurnBtn").style.display = "none";
    }

    closeYourTurnPopup() {
        document.getElementById("yourTurnPop").style.display = "none";
        document.getElementById("passTurnBtn").style.display = "inline-block";
    }


    disconnect() {
        if (this.stompClient != null) {
            this.stompClient.disconnect();
        }

        this.setConnected(false);
        console.log('Disconnected!');
        this.showGreeting("Au revoir!");
    }

    connectToGame() {
        this.closeHeroSelect();
        document.getElementById("connectToGame").style.display = "none";
        document.getElementById("cancelSearch").style.display = "block";
        this.stompClient.send(
            '/connectGame',
            {},
            this.selectedHero
        )
    }
    showGreeting(message) {
        this.greetings.push(message);
    }

    showMyHero(message) {
        console.log('message brut: ' + message);
        this.myHero.push(JSON.parse(message));
    }

    showHisHero(message) {
        console.log('message brut: ' + message);
        this.hisHero.push(JSON.parse(message));
    }

    showHand(message) {

        console.log('message brut: ' + message);

        var parsed = JSON.parse(message);
        console.log('parsed: ' + parsed);
        this.myCards = parsed;
        console.log("taille du tableau: " + this.myCards.length);

    }

    showMinions(message) {

        console.log('message brut de mes minions: ' + message);

        var parsed = JSON.parse(message);
        console.log('parsed mes minions: ' + parsed);
        this.myMinions = parsed;

    }

    showHisMinions(message) {

        console.log('message brut de ses minions: ' + message);

        var parsed = JSON.parse(message);
        console.log('parsed ses minions: ' + parsed);
        this.hisMinions = parsed;

    }

    showMyMana(message){

        console.log('message brut: ' + message);
        var parsed = JSON.parse(message);
        this.myMana = parsed;
    }

    showHisMana(message){
        console.log('message brut: ' + message);
        var parsed = JSON.parse(message);
        this.hisMana = parsed;
    }

    showHisHand(nbrCards){

        this.hisHand = new Array(nbrCards);

    }

    openHeroSelect() {
        document.getElementById("heroSelectPop").style.display = "block";
    }

    closeHeroSelect() {
        document.getElementById("heroSelectPop").style.display = "none";
    }

    selectHeroHandler(event: any) {
        this.selectedHero = event.target.value;
    }

    cancelSearch(){
        this.stompClient.send(
            '/disconnectGame',
            {}
        )
        document.getElementById("cancelSearch").style.display = "none";
        document.getElementById("connectToGame").style.display = "block";
    }

    openCardPopup(id) {
        document.getElementById("cardPopup"+id).style.display = "block";
    }

    closeCardPopup(id){
        document.getElementById("cardPopup"+id).style.display = "none";
    }

    playMinion(id){
        this.stompClient.send(
            '/playMinion',
            {},
            id
        )
        this.closeCardPopup(id);
    }
}
