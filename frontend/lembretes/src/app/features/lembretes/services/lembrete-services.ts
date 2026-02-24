import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { LembreteCreateDto } from '../models/lembrete-create.dto';
import { LembreteViewDtos } from '../models/lembrete-view.dto';

@Injectable({
  providedIn: 'root',
})
export class LembreteService {
  private http = inject(HttpClient)

  criarLembrete(payload:LembreteCreateDto){
    return this.http.post(`http://localhost:8080/api/lembrete/cadastrar`, payload)
  }

  listarLembretes(){
    return this.http.get<LembreteViewDtos[]>(`http://localhost:8080/api/lembrete/listar`)
  }

  concluirLembrete(id: number){
    return this.http.patch(`http://localhost:8080/api/lembrete/concluido/${id}`, null); 
  }

  excluirLembrete(id: number){
    
  }
}
