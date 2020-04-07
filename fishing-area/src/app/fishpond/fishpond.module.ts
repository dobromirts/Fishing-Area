import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CreateComponent } from './create/create.component';
import { ListComponent } from './list/list.component';
import { FishpondRoutingModule } from './fishpond-routing.module'
import { FormsModule} from '@angular/forms';
import { DetailsComponent } from './details/details.component'


@NgModule({
  declarations: [
    CreateComponent,
    ListComponent,
    DetailsComponent],
  imports: [
    CommonModule,
    FishpondRoutingModule,
    FormsModule
  ]
})
export class FishpondModule { }
