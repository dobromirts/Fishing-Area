import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './user/login/login.component';
import { RegisterComponent } from './user/register/register.component';
import {ListComponent} from './user/list/list.component'
import { NotFoundComponent } from './not-found/not-found.component';
import { MapComponent } from './map/map.component';
import { UserProfileComponent } from './user/user-profile/user-profile.component';
import { EditComponent } from './user/user-profile/edit/edit.component';
import { ContactsComponent } from './core/contacts/contacts.component';
import { AdminGuard } from './user/admin.guard';
import { LoggedGuard } from './user/logged.guard';
import { AuthGuard } from './user/auth.guard';
import { CatchComponent } from './user/user-profile/catch/catch.component';



const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    component: HomeComponent
  },
  {
    path: 'login',
    component: LoginComponent,
    canActivate: [LoggedGuard]

  },
  {
    path: 'register',
    component: RegisterComponent,
    canActivate: [LoggedGuard]
  },
  {
    path: 'users/all',
    component: ListComponent,
    canActivate: [AdminGuard]
  },
  {
    path: 'profile',
    component: UserProfileComponent,
    // canActivate: [AuthGuard]
    
  },
  {
    path: 'profile/edit',
    component: EditComponent,
    // canActivate: [AuthGuard]

  },
  {
    path: 'profile/catch',
    component: CatchComponent,
    // canActivate: [AuthGuard]

  },
  {
    path: 'map',
    component: MapComponent
  },
  {
    path: 'contacts',
    component: ContactsComponent,
  },
  {
    path: '**',
    component: NotFoundComponent
  }
  
];

export const AppRoutingModule = RouterModule.forRoot(routes);


