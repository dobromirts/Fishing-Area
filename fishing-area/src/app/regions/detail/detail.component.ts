import { Component, OnInit } from '@angular/core';
import { RegionModel } from '../region-binding.model';
import { RegionsService } from '../regions.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.scss']
})
export class DetailComponent implements OnInit {
  region: RegionModel;

  constructor(private regionService: RegionsService,private router:Router) { }

  ngOnInit() {
    this.regionService.findRegionByName(this.router.url.split("/")[3]).subscribe((reg:RegionModel)=>{
      this.region=reg;
    })
  }

}
