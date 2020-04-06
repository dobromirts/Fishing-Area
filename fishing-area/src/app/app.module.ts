import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CoreModule } from './core/core.module'
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HomeComponent } from './home/home.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { UserModule } from './user/user.module';
import {RegionsModule  } from './regions/regions.module';
import {FishpondModule} from './fishpond/fishpond.module'

import {HttpClientModule} from '@angular/common/http';
import { MapComponent } from './map/map.component' //can be deleted from here after test






@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NotFoundComponent,
    MapComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    CoreModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    UserModule,
    RegionsModule,
    FishpondModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
