import { Component, OnInit } from '@angular/core';
import { FishService } from '../fish.service';
import { FishDetailsModel } from './fish-details.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.scss']
})
export class DetailsComponent implements OnInit {
  fish: FishDetailsModel;

  constructor(private fishService:FishService,private router:Router) { }

  ngOnInit() {
    this.fishService.findFishById(this.router.url.split('/')[3]).subscribe((data:FishDetailsModel)=>{
      this.fish=data;
      console.log(this.fish)
    })
  }

}
