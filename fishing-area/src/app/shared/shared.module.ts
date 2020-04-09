import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LimitToPipe } from './pipes/limit-to.pipe';



@NgModule({
  declarations: [LimitToPipe],
  imports: [
    CommonModule
  ],
})
export class SharedModule { }
