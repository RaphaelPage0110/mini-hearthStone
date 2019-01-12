import {Component} from '@angular/core';
import {Stomp} from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';
import {Mana} from "./mana/mana.model";
import {Hero} from "./hero/hero.model";

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})
export class AppComponent {
    title = 'mini Hearthstone';
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

    /**
     * connect the client to the game server
     */
    connect() {
        const socket = new SockJS('http://localhost:8080/endpoint');
        this.stompClient = Stomp.over(socket);

        const _this = this;
        this.stompClient.connect({}, function () {
            _this.setConnected(true);
            _this.showGreeting("Vous êtes bien connecté au serveur du mini-hearthstone");

            _this.stompClient.subscribe('/user/queue/reply', function (resp) {
                _this.showGreeting(JSON.parse(resp.body).greeting);
            });

            _this.stompClient.subscribe('/user/queue/reply_myHand', function (resp) {
                _this.showHand(resp.body);
            });

            _this.stompClient.subscribe('/user/queue/reply_gameFound', function (resp) {
                AppComponent.gameReady();
                _this.showGreeting(JSON.parse(resp.body).greeting);
            });

            _this.stompClient.subscribe('/user/queue/reply_hisHand', function (resp) {
                _this.showHisHand(JSON.parse(resp.body).nbrCards);
            });

            _this.stompClient.subscribe('/user/queue/reply_myHero', function (resp) {
                _this.showMyHero(resp.body);
            });

            _this.stompClient.subscribe('/user/queue/reply_hisHero', function (resp) {
                _this.showHisHero(resp.body);
            });

            _this.stompClient.subscribe('/user/queue/reply_myMana', function (resp) {
                _this.showMyMana(resp.body);
            });

            _this.stompClient.subscribe('/user/queue/reply_hisMana', function (resp) {
                _this.showHisMana(resp.body);
            });

            _this.stompClient.subscribe('/user/queue/reply_yourTurn', function (resp) {
                document.getElementById("passTurnBtn").style.display = "inline-block";
                AppComponent.openYourTurnPopup(resp.body);
            });

            _this.stompClient.subscribe('/user/queue/reply_passedTurn', function () {
                document.getElementById("passTurnBtn").style.display = "none";
            });

            _this.stompClient.subscribe('/user/queue/reply_gameOver', function (resp) {
                _this.gameOver(resp.body);
            });

            _this.stompClient.subscribe('/user/queue/reply_playMinion', function (resp) {
                _this.showMinions(resp.body);
            });

            _this.stompClient.subscribe('/user/queue/reply_playedMinion', function (resp) {
                _this.showHisMinions(resp.body);
                let hasTaunt = false;
                for (let minion of _this.hisMinions) {
                    if(minion.taunt){
                        hasTaunt = true;
                    }
                }
                _this.canAttackHero = !hasTaunt;
            });

            _this.stompClient.subscribe('/user/queue/reply_targetsMinions', function (resp) {
                _this.showTargets(resp.body);
            });
        });

        document.getElementById("gameSearch").style.display = "block";
    }

    /**
     * disconnect the client from the game server
     */
    disconnect() {
        document.getElementById("gameSearch").style.display = "none";
        document.getElementById("theBoard").style.display = "none";
        this.cancelSearch();
        if (this.stompClient != null) {
            this.stompClient.disconnect();
        }
        this.setConnected(false);
        this.showGreeting("Au revoir!");
    }

    static gameReady() {
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
        );
        this.passTurn();
    }

    /**
     * resets the board when the game is over
     * @param resp
     */
    gameOver(resp) {
        document.getElementById("passTurnBtn").style.display = "none";
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
        this.greetings = [];
        this.myTargets = [];
        this.myMana = null;
        this.hisMana = null;
        this.canAttackHero = true;
        this.minionThatAttackId = null;
        this.spellThatAttackId = null;
        this.spellTargets = [];
    }

    static openYourTurnPopup(resp) {
        document.getElementById("yourTurnPop").style.display = "none";
        document.getElementById("yourTurnPopMessage").innerHTML = resp;
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

    closeYourTurnPopup() {
        document.getElementById("yourTurnPop").style.display = "none";
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

    /**
     * display a message on the player's screen, inside the "messages serveur" box
     * @param message
     */
    showGreeting(message) {
        this.greetings.push(message);
    }

    /**
     * display the hero of the player
     * @param message
     */
    showMyHero(message) {
        this.myHero = JSON.parse(message);
    }

    /**
     * display the opponent's hero
     * @param message
     */
    showHisHero(message) {
        this.hisHero = JSON.parse(message);
    }

    /**
     * Displays the hand of the player
     * @param message
     */
    showHand(message) {
        this.myCards = JSON.parse(message);
    }

    showMinions(message) {
        this.myMinions = JSON.parse(message);

    }

    showHisMinions(message) {
        this.hisMinions = JSON.parse(message);
    }

    /**
     * displays the player mana
     * @param message
     */
    showMyMana(message){
        this.myMana = JSON.parse(message);
    }

    /**
     * displays the opponent's mana
     * @param message
     */
    showHisMana(message){
        this.hisMana = JSON.parse(message);
    }

    /**
     * displays the back of the opponents hand
     * @param nbrCards
     */
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
        );
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
        );
        this.closeCardPopup(id);
    }

    playSpell(id) {
        this.stompClient.send(
            '/playSpell',
            {},
            id
        );
        this.closeCardPopup(id);
    }

    showTargetForMinion(id){
        this.minionThatAttackId = id;
        this.stompClient.send(
            '/showTargetForMinion',
            {},
            id
        );
        this.closeCardPopup(id);
    }

    showTargets(message) {
        this.myTargets = JSON.parse(message);
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
        let message = {attackerID: this.minionThatAttackId, targetID: targetID};
        this.stompClient.send(
            '/attackThisMinion',
            {},
            JSON.stringify(message)
        );
        this.closeTargetDetails(targetID);
        this.closeTargetPopup();
    }

    castSpellOnTarget(targetID){
        let message = {spellID: this.spellThatAttackId, targetID: targetID};
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
        this.stompClient.send(
            '/useHeroPowerOnTarget',
            {},
            targetID
        );
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
        this.stompClient.send(
            '/useHeroPowerOnTarget',
            {},
            "hero"
        );
        this.closeTargetHeroPowerPopup();
        this.closeHeroPowerPopup();
    }

    openHeroPowerTargetList(){
        document.getElementById("targetHeroPowerDetails").style.display = "block";
    }

}
