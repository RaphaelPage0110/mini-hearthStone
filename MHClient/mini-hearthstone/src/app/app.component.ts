import {Component} from '@angular/core';
import {Stomp} from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';
import {GameComponent} from "./game/game.component";
import {Mana} from "./mana.model";
import {Hero} from "./hero.model";

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
    myTargets: any[] = [];
    hisMinions: any[] = [];
    myCards: any[] = [];
    myMana: Mana;
    hisMana: Mana;
    hisHand : any[] = [];
    myHero : Hero;
    hisHero : Hero;
    disabled = true;
    canAttackHero : boolean = true;
    selectedHero : string = 'Jaina';
    minionThatAttackId : string;
    spellThatAttackId : string;
    spellTargets : any[] = [];
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
                let hasTaunt = false;
                for (let minion of _this.hisMinions) {
                    if(minion.taunt){
                        hasTaunt = true;
                    }
                }
                if(hasTaunt){
                    _this.canAttackHero = false;
                } else {
                    _this.canAttackHero = true;
                }
            });

            _this.stompClient.subscribe('/user/queue/reply_targetsMinions', function (resp) {
                console.log("La liste de vos cibles: "+resp.body)
                _this.showTargets(resp.body);
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
        this.myHero = null;
        this.hisHero = null;
        this.myMinions = [];
        this.hisMinions = [];
    }

    openYourTurnPopup() {
        document.getElementById("yourTurnPopMessage").innerHTML = "<h4><b>C'est à vous!</b></h4>";
        document.getElementById("yourTurnPop").style.display = "block";
    }

    openPromptSpellTarget(idCard) {
        document.getElementById("spellPopupShowTarget").style.display = "block";
        this.spellThatAttackId = idCard;
    }

    closeTargetSpellPopup() {
        document.getElementById("spellPopupShowTarget").style.display = "none";
    }

    showAlliedTargets() {
        this.spellTargets = this.myMinions;
        this.closeYourTurnPopup();
        document.getElementById("targetSpellDetails").style.display = "block";
    }

    showEnemiesTargets() {
        this.spellTargets = this.hisMinions;
        this.closeYourTurnPopup();
        document.getElementById("targetSpellDetails").style.display = "block";
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
        this.myHero = JSON.parse(message);
    }

    showHisHero(message) {
        console.log('message brut: ' + message);
        this.hisHero = JSON.parse(message);
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

    playSpell(id) {
        this.stompClient.send(
            '/playSpell',
            {},
            id
        )
        this.closeCardPopup(id);
    }

    showTargetForMinion(id){
        this.minionThatAttackId = id;
        this.stompClient.send(
            '/showTargetForMinion',
            {},
            id
        )
        this.closeCardPopup(id);
    }

    showTargets(message) {
        var parsed = JSON.parse(message);
        this.myTargets = parsed;
        document.getElementById("cardPopupShowTarget").style.display = "block";
    }

    closeTargetPopup(){
        document.getElementById("cardPopupShowTarget").style.display = "none";
        document.getElementById("targetDetails").style.display = "none";
    }

    openTargetPopup() {
        document.getElementById("targetDetails").style.display = "block";
    }


    openTargetDetails(targetID){
        document.getElementById("targetDetailsPopup"+targetID).style.display = "";
    }

    closeTargetDetails(targetID){
        document.getElementById("targetDetailsPopup"+targetID).style.display = "none";
    }

    attackThisMinion(targetID){
        console.log("Id de la carte qui attaque  "+this.minionThatAttackId);
        console.log("Id de la carte qui est attaquée  "+targetID);
        var message = {attackerID: this.minionThatAttackId, targetID: targetID};
        this.stompClient.send(
            '/attackThisMinion',
            {},
            JSON.stringify(message)
        );
        this.closeTargetDetails(targetID);
        this.closeTargetPopup();
    }

    castSpellOnTarget(targetID){
        console.log("Id du qui attaque  "+this.spellThatAttackId);
        console.log("Id de la carte qui est attaquée  "+targetID);
        var message = {spellID: this.spellThatAttackId, targetID: targetID};
        this.stompClient.send(
            '/castSpellOnThisMinion',
            {},
            JSON.stringify(message)
        );
        this.closeTargetDetails(targetID);
        this.closeTargetSpellPopup();
    }

    attackHero(){
        this.stompClient.send(
            '/attackHero',
            {},
            this.minionThatAttackId
        );
        this.closeTargetPopup();
    }

    openHeroPowerPopup(){
        document.getElementById("heroPowerPopup").style.display = "block";
    }

    closeHeroPowerPopup(){
        document.getElementById("heroPowerPopup").style.display = "none";
    }

    closeTargetHeroPowerPopup(){
        document.getElementById("heroPowerPopupShowTarget").style.display = "none";
    }

    openPromptHeroPowerTarget(){
        document.getElementById("heroPowerPopupShowTarget").style.display = "block";
    }

    castHeroPowerOnTarget( targetID ){
        this.closeTargetHeroPowerPopup();
        this.closeHeroPowerPopup();
    }

    useHeroPower(){
        this.stompClient.send(
            '/useHeroPower',
            {}
        );
        this.closeHeroPowerPopup();
    }

    castHeroPowerOnHero(){
        this.closeTargetHeroPowerPopup();
        this.closeHeroPowerPopup();
    }

    openHeroPowerTargetList(){
        document.getElementById("targetHeroPowerDetails").style.display = "block";
    }

}
