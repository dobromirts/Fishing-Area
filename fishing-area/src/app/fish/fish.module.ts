import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CreateComponent } from './create/create.component';
import { DetailsComponent } from './details/details.component';
import { FishRoutingModule } from './fish-routing.module';
import { FormsModule} from '@angular/forms';



@NgModule({
  declarations: [CreateComponent, DetailsComponent],
  imports: [
    CommonModule,
    FishRoutingModule,
    FormsModule
  ]
})
export class FishModule { }
