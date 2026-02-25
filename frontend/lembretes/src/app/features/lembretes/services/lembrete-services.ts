import { HttpClient, HttpParams } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { LembreteCreateDto } from '../models/lembrete-create.dto';
import { LembreteViewDtos } from '../models/lembrete-view.dto';
import { Page } from '../../../core/models/page-response';

@Injectable({
  providedIn: 'root',
})
export class LembreteService {
  private http = inject(HttpClient)
  

  criarLembrete(payload:LembreteCreateDto){
    return this.http.post(`/api/lembrete/cadastrar`, payload)
  }

  listarLembretes(){
    return this.http.get<LembreteViewDtos[]>(`/api/lembrete/listar`)
  }

  buscarLembretes(page: number, size: number){
    let params = new HttpParams()
    params = params.set('page', String(page))
    params = params.set('size',String(size))
    return this.http.get<Page<LembreteViewDtos>>(`/api/lembrete/buscar`, {params})
  }


  concluirLembrete(id: number){
    return this.http.patch(`/api/lembrete/concluido/${id}`, null); 
  }

  excluirLembrete(id: number){
    
  }
}
