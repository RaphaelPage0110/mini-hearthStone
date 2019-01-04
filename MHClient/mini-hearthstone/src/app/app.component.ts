import { Component } from '@angular/core';
import {Stomp} from'@stomp/stompjs';
import * as SockJS from 'sockjs-client';
import {GameComponent} from "./game/game.component";

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
    myCards: any[] = [];
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


        });
    }

    gameReady() {
        document.getElementById("cancelSearch").style.display = "none";
        document.getElementById("theBoard").style.display = "block";
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
}
