import { Component, input } from '@angular/core';

@Component({
  selector: 'app-game-profile-coins',
  imports: [],
  templateUrl: './game-profile-coins.html',
  styleUrl: './game-profile-coins.css',
})
export class GameProfileCoins {

  readonly moedas = input<number>(0)
}
