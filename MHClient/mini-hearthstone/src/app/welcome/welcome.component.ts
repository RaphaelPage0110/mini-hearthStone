import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {

  message="Bienvenue sur mini Hearthstone! Cliquez sur le bouton ci-dessous pour commencer à jouer!"
  constructor() { }

  ngOnInit() {
  }

}
