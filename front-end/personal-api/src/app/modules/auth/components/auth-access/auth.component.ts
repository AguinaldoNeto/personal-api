import { AuthService } from './../../auth.service';
import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-auth',
  imports: [RouterModule],
  templateUrl: './auth.component.html',
  styleUrl: './auth.component.scss',
  providers: [AuthService]
})
export class AuthComponent {

  constructor(private authService: AuthService) { }

  ngOnInit() {
    this.getUsers();
  }

  getUsers() {
    this.authService.getUsers().subscribe(users => {
      console.log(users);
    });
  }


}
