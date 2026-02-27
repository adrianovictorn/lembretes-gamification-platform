import { Directive, ElementRef, effect, input } from '@angular/core';
import autoAnimate, { AutoAnimateOptions } from '@formkit/auto-animate';

@Directive({
  selector: '[autoAnimate]',
  standalone: true,
})
export class AutoAnimateDirective {
  options = input<Partial<AutoAnimateOptions>>({});

  constructor(elementRef: ElementRef<HTMLElement>) {
    effect(() => {
      autoAnimate(elementRef.nativeElement, this.options());
    });
  }
}
