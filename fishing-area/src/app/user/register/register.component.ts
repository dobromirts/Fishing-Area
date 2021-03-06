import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { RegisterModel } from './register-binding.model';
import { AuthService } from 'src/app/user/auth.service';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss','../../../error-styles.scss']
})
export class RegisterComponent implements OnInit {
  registerModel: RegisterModel;
  errorOccurred=false;
  errorMessage:string;
  constructor(
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit() {
    this.registerModel=new RegisterModel();
  }

  register(){
    this.authService.register(this.registerModel).subscribe(data=>{
      if(data){
        this.router.navigate(["/login"])
      }
    },(error: HttpErrorResponse) => {
      this.errorOccurred = true;
      this.errorMessage=error.error.message;
  });
    
  }

  // noMatch() {
  //   if(this.signupForm.get('password').value !== this.signupForm.get('confirmPassword').value){
  //     this.signupForm.get('confirmPassword').setErrors({'incorrect': true})
  //   } else {
  //     this.signupForm.get('confirmPassword').setErrors(null);
  //   }
  //   return this.signupForm.get('password').value !== this.signupForm.get('confirmPassword').value;
  // }

  

}
