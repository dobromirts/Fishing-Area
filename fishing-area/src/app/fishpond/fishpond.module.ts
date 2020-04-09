import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CreateComponent } from './create/create.component';
import { ListComponent } from './list/list.component';
import { FishpondRoutingModule } from './fishpond-routing.module'
import { FormsModule} from '@angular/forms';
import { DetailsComponent } from './details/details.component';
import { EditComponent } from './edit/edit.component'


@NgModule({
  declarations: [
    CreateComponent,
    ListComponent,
    DetailsComponent,
    EditComponent],
  imports: [
    CommonModule,
    FishpondRoutingModule,
    FormsModule
  ]
})
export class FishpondModule { }
