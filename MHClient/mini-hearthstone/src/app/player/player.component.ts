import { Component, OnInit } from '@angular/core';
import {Player} from "../player";

@Component({
  selector: 'app-player',
  templateUrl: './player.component.html',
  styleUrls: ['./player.component.css']
})
export class PlayerComponent implements OnInit {

  player : Player[];
  constructor() { }

  ngOnInit() {
  }

}
