import { Component, OnInit, OnChanges } from '@angular/core';
import { RegionsService } from '../regions.service';
import { RegionModel } from '../region-binding.model';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.scss']
})
export class CreateComponent implements OnInit{
  regionModel: RegionModel;
  errorOccurred=false;
  

  constructor(private regionService:RegionsService,private router:Router) {}

  
  ngOnInit() {
    this.regionModel=new RegionModel();
  }
  
  addRegion(){
    this.regionService.addRegion(this.regionModel).subscribe(data=>{
      this.router.navigate(['/regions/all'])
    },(error: HttpErrorResponse) => {
      this.errorOccurred = true;
  });
  }

  

}
