import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-waitingplayer',
  templateUrl: './waitingplayer.component.html',
  styleUrls: ['./waitingplayer.component.css']
})
export class WaitingplayerComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  message = "En attente d'un autre joueur..."
}
