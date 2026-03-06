import { Component, computed, inject } from '@angular/core';
import { GameProfileLevel } from "../game-profile-level/game-profile-level";
import { GameProfileCoins } from "../game-profile-coins/game-profile-coins";
import { GameProfileServices } from '../../services/game-profile-services';
import { toSignal } from '@angular/core/rxjs-interop';

@Component({
  selector: 'app-game-profile-view',
  imports: [GameProfileLevel, GameProfileCoins],
  templateUrl: './game-profile-view.html',
  styleUrl: './game-profile-view.css',
})
export class GameProfileView {

  private service = inject(GameProfileServices)

  readonly profile = toSignal(this.service.buscarGameProfile(), {initialValue: null})

  readonly nome = computed(() => this.profile()?.user.nome ?? '')
  readonly moedas = computed(() => this.profile()?.moedas ?? '')
  readonly level = computed(() => this.profile()?.xp)
  readonly role = computed(() => this.profile()?.user.role ?? '')
  
}
