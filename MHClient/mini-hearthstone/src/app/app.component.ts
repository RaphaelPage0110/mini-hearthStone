import { Component } from '@angular/core';
import {Stomp} from'@stomp/stompjs';
import * as SockJS from 'sockjs-client';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'mini Hearthstone';
  description = 'Angular-WebSocket Demo';

  greetings: string[] = [];
  disabled = true;
  name: string;
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

    });
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
    this.stompClient.send(
      '/connect',
      {}
    )
  }
  showGreeting(message) {
    this.greetings.push(message);
  }
}
