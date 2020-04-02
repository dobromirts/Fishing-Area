import { Component, OnInit, OnChanges } from '@angular/core';
import { RegionsService } from '../regions.service';
import { RegionModel } from './regionModel';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.scss']
})
export class CreateComponent implements OnInit{
  regionModel: RegionModel;

  constructor(private regionService:RegionsService,private router:Router) {}

  
  ngOnInit() {
    this.regionModel=new RegionModel();
  }
  
  addRegion(){
    this.regionService.addRegion(this.regionModel).subscribe(data=>{
      console.log(data)
      this.router.navigate(['/regions/all'])
    },console.error);
  }

  

}
