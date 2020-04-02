import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CreateComponent } from './create/create.component';
import { DetailComponent } from './detail/detail.component';
import { ListComponent } from './list/list.component';
import { RegionsRoutingModule } from './regions-routing.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule} from '@angular/forms'




@NgModule({
  declarations: [CreateComponent, DetailComponent, ListComponent],
  imports: [
    RegionsRoutingModule,
    CommonModule,
    NgbModule,
    FormsModule
  ]
  
  
})
export class RegionsModule { }
