import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule,ReactiveFormsModule  } from '@angular/forms'
import {HttpClientModule} from '@angular/common/http'
import {LoginComponent} from './login/login.component'
import {RegisterComponent} from './register/register.component'
import {RouterModule} from '@angular/router';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { ListComponent } from './list/list.component';
import { EditComponent } from './user-profile/edit/edit.component';
import { CatchComponent } from './user-profile/catch/catch.component'



@NgModule({
  declarations: [RegisterComponent,LoginComponent, UserProfileComponent, ListComponent, EditComponent, CatchComponent],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule
  ]
})
export class UserModule { }
