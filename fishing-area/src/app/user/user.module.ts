import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule,ReactiveFormsModule  } from '@angular/forms'
import {HttpClientModule} from '@angular/common/http'
import {LoginComponent} from './login/login.component'
import {RegisterComponent} from './register/register.component'



@NgModule({
  declarations: [RegisterComponent,LoginComponent],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ]
})
export class UserModule { }
