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

import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import { MapComponent } from './map/map.component' //can be deleted from here after test
import { FishModule } from './fish/fish.module';
import { SharedModule } from './shared/shared.module';
import { AuthInterceptor } from './auth-interceptor';




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
    SharedModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    UserModule,
    RegionsModule,
    FishpondModule,
    FishModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
