import { Component, OnInit, OnChanges } from '@angular/core';
import { RegionModel } from '../region-binding.model';
import { RegionsService } from '../regions.service';
import { Router } from '@angular/router';
import { FishpondService } from 'src/app/fishpond/fishpond.service';
import { FishpondAllModel } from 'src/app/fishpond/list/fishpond-all.model';
import { tap } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.scss']
})
export class DetailComponent implements OnInit {
  private baseUrl="http://localhost:8080/api";
  region: RegionModel;
  fishponds: FishpondAllModel[];

  // get fishponds() {
  //   return this.fishpondService.fishponds;
  // };

  constructor(private regionService: RegionsService,private fishpondService:FishpondService, private router:Router,private http:HttpClient) { }

  ngOnInit() {
    this.regionService.findRegionByName(this.router.url.split("/")[3]).subscribe((reg:RegionModel)=>{
      this.region=reg;
    });
    this.allFishopondsByRegion(this.router.url.split("/")[3]).subscribe();
  }
  allFishopondsByRegion(name: string){
    return this.http.get<FishpondAllModel[]>(`${this.baseUrl}/fishpond/all/${name}`).pipe(
      tap((fishponds)=>{
        this.fishponds=[].concat(fishponds);
      })
    )
  }

}
