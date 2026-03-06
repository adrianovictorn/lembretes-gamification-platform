import { afterNextRender, Component, DestroyRef, ElementRef, EventEmitter, inject, input, Input, output, Output, viewChild } from '@angular/core';
import { LembreteViewDtos } from '../../models/lembrete-view.dto';
import { gsap } from 'gsap';
import { NgClass } from '@angular/common';

@Component({
  selector: 'app-lembrete-card',
  standalone: true,
  imports: [NgClass],
  templateUrl: './lembrete-card.html',
  styleUrl: './lembrete-card.css',
})
export class LembreteCard {

  item = input.required<LembreteViewDtos>()
  concluir = output<number>();
  rewardDone = output<boolean>();
  concluidoOk = input<boolean>();

  private destroyRef = inject(DestroyRef)
  private cardRef = viewChild.required<ElementRef<HTMLElement>>('card')
  private rewardRef = viewChild.required<ElementRef<HTMLElement>>('reward')
  private rewardBlur = viewChild.required<ElementRef<HTMLElement>>('reward_blur')
  private xpEl = viewChild.required<ElementRef<HTMLElement>>('xpEl');
private coinEl = viewChild.required<ElementRef<HTMLElement>>('coinEl');  

  constructor(){
    afterNextRender(() => {
      const card= this.cardRef().nativeElement;
      gsap.fromTo(
        card,
        { y: 18, opacity: 0, scale: 0.98, filter: 'blur(2px)'},
        { y: 0, opacity: 1, scale: 1, filter: 'blur(0px)', duration: 1.5, ease: 'expo.out' }

      )



      const hoverIn = () => {
        gsap.to(card, {scale: 1.05, y: -3, filter: 'brightness(1.2) contrast(1.02) saturate(1.04) drop-shadow(0px 0px 20px rgba(34,211,238, .35)) ', duration: 0.15,  ease: 'power1.out', overwrite: 'auto'})
      }

      const hoverOut = () => {
        gsap.to(card, { scale: 1.0, y: 0, filter: 'none', duration: 0.15, ease: 'power1.out', overwrite: 'auto' })
      }

      card.addEventListener('pointerenter',hoverIn)
      card.addEventListener('pointerleave', hoverOut)

      this.destroyRef.onDestroy(() => {

        card.removeEventListener('pointerenter', hoverIn)
        card.removeEventListener('pointerleave', hoverOut)
      })
      
    })
  }

  private floatUp(sourceEL: HTMLElement, text: string){
    const rect = sourceEL.getBoundingClientRect()

    const token = document.createElement('div')
    token.textContent = text
    token.className = 'fixed z-[9999] pointer-events-none font-bold text-sm';
    token.style.left = `${rect.left + rect.width / 2}px`;
    token.style.top = `${rect.top + rect.height / 2}px`;
    token.style.transform = 'translate(-50%, -50%)';
    token.style.color = 'white';
    token.style.background = 'rgba(2, 6, 23, 0.85)'; // slate-950
    token.style.border = '1px solid rgba(34, 211, 238, 0.35)';
    token.style.padding = '6px 10px';
    token.style.borderRadius = '10px';

    document.body.appendChild(token);

    gsap.fromTo(
      token, 
      {y:0, opacity: 0, scale: 0.9},
      {
        y: -60,
        opacity: 1,
        scale: 1,
        duration: 0.18,
        ease: 'power2.out',
        overwrite: 'auto',
        onComplete: () => {
          gsap.to(token, {
            y: -110,
            opacity: 0,
            scale: 1.05, 
            duration: 1,
            ease: 'power3.in',
            onComplete: () => token.remove()
          })
        }
      }
    )
  }
  formatarDataHorario(valor: any){
    return new Date(valor).toLocaleString("pt-BR")
  }

  onConcluir(){
    if(this.concluidoOk()){
      const reward = this.rewardRef().nativeElement
      const rewardBg = this.rewardBlur().nativeElement
      gsap.set([reward, rewardBg], { opacity: 1 });
      gsap.fromTo(reward, {y:12, scale: 1.05}, {y: 0, scale: 1.5, duration: 0.25, ease: 'power2.out'})
      gsap.fromTo(rewardBg, {opacity: 0}, {opacity: 1, duration: 0.2, ease: 'power1.out'})

      gsap.to([reward, rewardBg], {
        y: -10,
        opacity: 0,
        duration: 0.25,
        delay: 3,
        ease: 'power2.in',
      });

      this.floatUp(this.xpEl().nativeElement, `+${this.item().xpReward} XP`)
      this.floatUp(this.coinEl().nativeElement, `+${this.item().coinsReward}  🪙`)
      this.concluir.emit(this.item().id)
      this.rewardDone.emit(true)
    }
  }

  
}
