import { inject, Injectable, signal } from '@angular/core';
import { GameProfileServices } from './game-profile-services';
import { GameProfileViewDTO } from '../models/game-profile-view-dto';

@Injectable({
  providedIn: 'root',
})
export class GameProfileFacade {
  private service = inject(GameProfileServices)
  gameProfile = signal<GameProfileViewDTO | null>(null)

  carregar(){
    this.service.buscarGameProfile().subscribe({
      next: (res) => {
        this.gameProfile.set(res)
        console.log(this.gameProfile)
      }
    })
  }
}
