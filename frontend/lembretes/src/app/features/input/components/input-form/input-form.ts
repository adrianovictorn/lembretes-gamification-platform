import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { IaLembreteRequest } from '../../models/ia-lembrete-request';
import { InputServices } from '../../services/input-services';
import { Router } from '@angular/router';

@Component({
  selector: 'app-input-form',
  imports: [ReactiveFormsModule],
  templateUrl: './input-form.html',
  styleUrl: './input-form.css',
})
export class InputForm {

  form: FormGroup;

  constructor(private fb: FormBuilder, private inputService: InputServices, private route: Router){
    this.form = this.criarForm()

  }

  criarForm(): FormGroup{
    return this.fb.group({
      text: ['',[Validators.required, Validators.maxLength(155), Validators.minLength(10)]]
    })
  }

  salvar(){
    if(this.form.invalid){
      this.form.markAllAsTouched();
      return; 
    }

    let request: IaLembreteRequest
    request = this.form.getRawValue();
    this.inputService.criarComIa(request).subscribe({
      next: (res) => {
        alert(`Sucesso ao enviar requisição: ${res}`)
        this.route.navigate([''])

      },
      error: (error) => {
        throw Error(`Erro ao realizar requisição: ${error}`)
      }
    })
  }


}
