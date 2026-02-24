import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { GameProfileViewDTO } from '../models/game-profile-view-dto';

@Injectable({
  providedIn: 'root',
})
export class GameProfileServices {
  private http = inject(HttpClient)

  buscarGameProfile(){
    return this.http.get<GameProfileViewDTO>(`http://localhost:8080/api/game-profile/buscar/autenticado`)
  }
}
