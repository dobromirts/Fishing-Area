import { Component, OnInit } from '@angular/core';
import { FishpondService } from '../fishpond.service';
import { FishpondAddModel } from '../create/fishpond-add.model';
import { Router } from '@angular/router';
import { tap } from 'rxjs/operators';

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.scss']
})
export class EditComponent implements OnInit {
  fishpond: FishpondAddModel;

  constructor(private fishpondService:FishpondService,private router:Router) { }

  ngOnInit() {
    this.fishpond=new FishpondAddModel;
    this.fishpondService.findFishpondById(this.router.url.split("/")[3]).subscribe((data:FishpondAddModel)=>{
      this.fishpond=data;
      console.log(this.fishpond)
    })
  }

  editFishpond(){

  }

  delete(){
    this.fishpondService.deleteFishPond(this.fishpond.id).subscribe();
    this.router.navigate(["/"])
  }

}
