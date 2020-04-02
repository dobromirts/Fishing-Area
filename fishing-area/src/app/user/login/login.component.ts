import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth/auth.service';
import { SignInModel } from './singInModel';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss','../../../error-styles.scss']
})
export class LoginComponent implements OnInit{
 
  signInModel: SignInModel;
  rememberMe=false;

  constructor(
    private router: Router,
    private authService: AuthService
  ) { }
  ngOnInit() {
    this.signInModel=new SignInModel();
  }

  signin() {
    this.authService.signIn(this.signInModel).subscribe(data => {
      this.authService.handleAuthentication(data.headers.get('Authorization'), this.rememberMe);
      this.router.navigate(['/']);
    },console.error);
  }




}
