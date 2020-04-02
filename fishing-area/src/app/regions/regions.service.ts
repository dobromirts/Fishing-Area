import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { RegionModel } from './create/regionModel';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class RegionsService {
  private baseUrl="http://localhost:8080/api";
  regions: RegionModel[];

  constructor(private http: HttpClient) { }

  addRegion(regionModel: RegionModel){
    return this.http.post(`${this.baseUrl}/regions/add`,regionModel)
  }

  allRegions(){
    return this.http.get<RegionModel[]>(`${this.baseUrl}/regions/all`).pipe(
      tap((regions)=>{
        this.regions=[].concat(regions);
      })
    )
  }

  findRegionByName(name: string){
    return this.http.get(`${this.baseUrl}/regions/${name}`)
  }
}
