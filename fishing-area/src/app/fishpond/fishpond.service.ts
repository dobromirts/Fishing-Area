import { Injectable } from '@angular/core';
import { FishpondAddModel } from './create/fishpond-add.model';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs/operators';
import { FishpondAllModel } from './list/fishpond-all.model';

@Injectable({
  providedIn: 'root'
})
export class FishpondService {
  private baseUrl="http://localhost:8080/api";
  fishponds: FishpondAllModel[]

  constructor(private http:HttpClient) { }


  addFishopond(formData: FormData){
    return this.http.post(`${this.baseUrl}/fishpond/add`,formData)
  }

  findFishpondById(id: string){
    return this.http.get(`${this.baseUrl}/fishpond/${id}`)
  }

  deleteFishPond(id: string){
    return this.http.delete(`${this.baseUrl}/fishpond/delete/${id}`)
  }

  allFishponds(){
    return this.http.get(`${this.baseUrl}/fishpond/all`).pipe(
      tap((fishpond:FishpondAddModel)=>{
        this.fishponds=[].concat(fishpond)
      })
    )
  }
}
