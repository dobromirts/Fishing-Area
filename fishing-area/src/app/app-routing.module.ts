import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './user/login/login.component';
import { RegisterComponent } from './user/register/register.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { MapComponent } from './map/map.component';
import { UserProfileComponent } from './user/user-profile/user-profile.component';
import { ContactsComponent } from './core/contacts/contacts.component';


const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    component: HomeComponent
  },
  {
    path: 'login',
    component: LoginComponent,

  },
  {
    path: 'register',
    component: RegisterComponent,
  },
  {
    path: 'profile',
    component: UserProfileComponent
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


