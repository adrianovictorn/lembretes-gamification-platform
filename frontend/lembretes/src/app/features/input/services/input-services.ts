import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { IaLembreteRequest } from '../models/ia-lembrete-request';

@Injectable({
  providedIn: 'root',
})
export class InputServices {
  
  private http = inject(HttpClient)

  criarComIa(request: IaLembreteRequest){
    return this.http.post("http://localhost:8080/api/lembrete/ai", request);
  }

}
