import { Component, EventEmitter, Input, Output } from '@angular/core';
import { LembreteViewDtos } from '../../models/lembrete-view.dto';
import { LembreteService } from '../../services/lembrete-services';

@Component({
  selector: 'app-lembrete-card',
  standalone: true,
  imports: [],
  templateUrl: './lembrete-card.html',
  styleUrl: './lembrete-card.css',
})
export class LembreteCard {
  @Input({ required: true}) item!: LembreteViewDtos
  @Output() excluir = new EventEmitter<LembreteViewDtos>();
  @Output() concluir = new EventEmitter<void>();

  constructor(private lembreteService: LembreteService){}

  onConcluir(){
    console.log(this.item.id)
    this.lembreteService.concluirLembrete(this.item.id).subscribe({
      next: (res) => {

        
        alert("Lembrete atualizado com sucesso !");
        this.lembreteService.listarLembretes()
      },
      error: (error) => {
        throw  Error(error)
        
      } 
      })
  }

  onExcluir(){
    
  }
}
