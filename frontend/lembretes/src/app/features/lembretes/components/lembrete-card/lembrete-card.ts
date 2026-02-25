import { Component, EventEmitter, input, Input, output, Output } from '@angular/core';
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

  item = input.required<LembreteViewDtos>()
  concluir = output<number>();
  
  onConcluir(){
    this.concluir.emit(this.item().id)
  }

  
}
