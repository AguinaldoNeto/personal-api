import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-forgot-password',
  imports: [ReactiveFormsModule, RouterModule],
  templateUrl: './forgot-password.component.html',
  styleUrl: './forgot-password.component.scss'
})
export class ForgotPasswordComponent {

  sendPasswordForm: FormGroup;

  constructor(private form: FormBuilder) {
    this.sendPasswordForm = this.form.group({
      email: ['', [Validators.required, Validators.email]]
    });
  }

  onSubmit() {
    if (this.sendPasswordForm.valid) {
      console.log('Form submitted', this.sendPasswordForm.value);

      // Call API to send password reset link
      // inserir mensagem genérica de sucesso
    }
  }
}
