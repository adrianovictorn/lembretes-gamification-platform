import { afterNextRender, Component, DestroyRef, ElementRef, EventEmitter, inject, input, Input, output, Output, viewChild } from '@angular/core';
import { LembreteViewDtos } from '../../models/lembrete-view.dto';
import { gsap } from 'gsap';

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

  private destroyRef = inject(DestroyRef)
  private cardRef = viewChild.required<ElementRef<HTMLElement>>('card')
  private rewardRef = viewChild.required<ElementRef<HTMLElement>>('reward')

  constructor(){
    afterNextRender(() => {
      const card= this.cardRef().nativeElement;
      gsap.fromTo(
        card,
        {y: 18, opacity: 0, scale: 0.98, filter: 'blur(6px)'},
        { y: 0, opacity: 1, scale: 1, filter: 'blur(0px)', duration: 0.55, ease: 'elastic.out(1, 0.75)' }

      )

      const hoverIn = () => {
        gsap.to(card, {scale: 1.08, duration: 0.15, ease: 'power1.out', overwrite: 'auto'})
      }


      card.addEventListener('pointerenter',hoverIn)


      this.destroyRef.onDestroy(() => {

        card.removeEventListener('pointerenter', hoverIn)
      })
      
    })
  }
  
  formatarDataHorario(valor: any){
    return new Date(valor).toLocaleString("pt-BR")
  }

  onConcluir(){
    this.concluir.emit(this.item().id)
  }

  
}
