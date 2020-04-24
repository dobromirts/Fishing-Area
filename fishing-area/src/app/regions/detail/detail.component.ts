import { Component, OnInit, OnChanges } from '@angular/core';
import { RegionModel } from '../region-binding.model';
import { RegionsService } from '../regions.service';
import { Router } from '@angular/router';
import { FishpondService } from 'src/app/fishpond/fishpond.service';
import { FishpondAllModel } from 'src/app/fishpond/list/fishpond-all.model';
import { tap } from 'rxjs/operators';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { AuthService } from 'src/app/user/auth.service';


@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.scss']
})
export class DetailComponent implements OnInit {
  private baseUrl="http://localhost:8080/api";
  region: RegionModel;
  fishponds: FishpondAllModel[];
  userRole: string;
  errorOccurred=false;
  
  // get fishponds() {
  //   return this.fishpondService.fishponds;
  // };

  constructor(private regionService: RegionsService,private fishpondService:FishpondService,private authService:AuthService, private router:Router,private http:HttpClient) { }

  ngOnInit() {
    this.authService.user.subscribe(user => {
      this.userRole = user ? user.role : undefined;
    });
    this.regionService.findRegionByName(this.router.url.split("/")[3]).subscribe((reg:RegionModel)=>{
      this.region=reg;
    }),(error: HttpErrorResponse) => {
      this.errorOccurred = true};

    this.allFishopondsByRegion(this.router.url.split("/")[3]).subscribe(),(error: HttpErrorResponse) => {
      this.errorOccurred = true;};
  }
  allFishopondsByRegion(name: string){
    return this.http.get<FishpondAllModel[]>(`${this.baseUrl}/fishpond/all/${name}`).pipe(
      tap((fishponds)=>{
        this.fishponds=[].concat(fishponds);
      })
    )
  }

}
