import { Routes } from "@angular/router";
import { ForgotPasswordComponent } from "./components/forgot-password/forgot-password.component";
import { AuthComponent } from "./components/auth-access/auth.component";


export const AUTH_ROUTES: Routes = [
  {
    path: '',          // Rota: /auth
    component: AuthComponent
  },
  {
    path: 'forgot-password', // Rota: /auth/forgot-password
    component: ForgotPasswordComponent
  }
];
