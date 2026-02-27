import { Component, inject, signal } from '@angular/core';
import { LembreteCard } from "../lembrete-card/lembrete-card";
import { LembreteFacade } from '../../services/lembrete.facade';
import { AutoAnimateDirective } from '../../directives/auto-animate.directive';


@Component({
  selector: 'app-lembrete-list',
  standalone: true,
  imports: [AutoAnimateDirective,LembreteCard],
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

  enterClass = signal('enter-animation')
}
