import { Component, OnInit } from '@angular/core';
import { FishDetailsModel } from 'src/app/fish/details/fish-details.model';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs/operators';
import { Router } from '@angular/router';

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.scss']
})
export class DetailsComponent implements OnInit {
  fishes: FishDetailsModel[]
  private baseUrl="http://localhost:8080/api";
  constructor(private http:HttpClient,private router:Router) { }

  ngOnInit() {
    this.allFishesByFishpond(this.router.url.split("/")[3]).subscribe();
  }

  allFishesByFishpond(id: string){
    return this.http.get<FishDetailsModel[]>(`${this.baseUrl}/fish/all/${id}`).pipe(
      tap((fishes)=>{
        this.fishes=[].concat(fishes);
        console.log(this.fishes)
      })
    )
  }

}
