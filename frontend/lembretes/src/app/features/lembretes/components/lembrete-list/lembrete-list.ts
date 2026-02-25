import { Component, effect, inject, OnInit } from '@angular/core';
import { LembreteCard } from "../lembrete-card/lembrete-card";
import { LembreteFacade } from '../../services/lembrete.facade';

@Component({
  selector: 'app-lembrete-list',
  standalone: true,
  imports: [LembreteCard],
  templateUrl: './lembrete-list.html',
  styleUrl: './lembrete-list.css',
})
export class LembreteList {

  private facade = inject(LembreteFacade)

  vm = this.facade.vm

  ngOnInit(){
    this.facade.carregar()
  }


  next(){
    this.facade.proxima();
  }
  prev(){
    this.facade.anterior()
  }

  concluido(id: number){
    this.facade.concluir(id);
  }
}
