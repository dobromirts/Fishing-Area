import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FooterComponent } from './footer/footer.component';
import { HeaderComponent } from './header/header.component';
import {RouterModule} from '@angular/router'
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { ContactsComponent } from './contacts/contacts.component';




@NgModule({
  declarations: [FooterComponent, HeaderComponent, ContactsComponent],
  imports: [
    CommonModule,
    RouterModule,
    NgbModule
  ],
  providers: [],
  exports: [FooterComponent,HeaderComponent]

})
export class CoreModule { }
