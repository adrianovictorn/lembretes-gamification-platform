import { afterNextRender, computed, inject, Injectable, signal } from '@angular/core';
import { LembreteService } from './lembrete-services';
import { LembreteViewDtos } from '../models/lembrete-view.dto';
import { finalize, tap } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class LembreteFacade {
  
  private api = inject(LembreteService)
  page = signal(0)
  size = signal(10)
  totalPages = signal(0)

  lembretes = signal<LembreteViewDtos[]>([])
  loading = signal(false)

  vm = computed(() => ({
    page: this.page(),
    size: this.size(),
    totalPages: this.totalPages(),
    lembretes: this.lembretes(),
    loading: this.loading(),
    canPrev: this.page() > 0,
    canNext: this.page() < this.totalPages() - 1
  })
)


  carregar(page = this.page(), size = this.size()){
    this.loading.set(true)

    
    this.api.buscarLembretes(page, size)
      .pipe(finalize(() => this.loading.set(false)))
      .subscribe(res => {
        this.lembretes.set(res.content)
        this.page.set(page)
        this.totalPages.set(res.totalPages)
        console.log(res.content)
      })
  }

  proxima(){
    if(this.page() < this.totalPages() - 1){
      const next = this.page() + 1
      this.page.set(next)
      this.carregar(next)
    }
  }

  anterior(){
    if(this.page() > 0){
      this.page.update(p => p - 1)
      this.carregar()
    }
  }

  concluir(id: number){
    this.api.concluirLembrete(id)
    .pipe(
      tap(() => this.carregar()),
      finalize(() => this.loading.set(false))
    ).subscribe()
  }

}
