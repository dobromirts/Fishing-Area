import { Injectable } from '@angular/core';
import { FishpondAddModel } from './create/fishpond-add.model';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class FishpondService {
  private baseUrl="http://localhost:8080/api";
  fishponds: FishpondAddModel[]

  constructor(private http:HttpClient) { }

  // addFishopond(fishpondAddModel: FishpondAddModel){
  //   return this.http.post(`${this.baseUrl}/fishopond/add`,fishpondAddModel)
  // }
  addFishopond(formData: FormData){
    return this.http.post(`${this.baseUrl}/fishpond/add`,formData)
  }

  allFishoponds(){
    return this.http.get<FishpondAddModel[]>(`${this.baseUrl}/fishopond/all`).pipe(
      tap((fishponds)=>{
        this.fishponds=[].concat(fishponds);
      })
    )
  }

  findFishpondByName(name: string){
    return this.http.get(`${this.baseUrl}/fishpond/${name}`)
  }
}
