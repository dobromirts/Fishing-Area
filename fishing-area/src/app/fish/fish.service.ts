import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class FishService {
  private baseUrl="http://localhost:8080/api";

  constructor(private http:HttpClient) { }

  addFish(formData: FormData){
    return this.http.post(`${this.baseUrl}/fish/add`,formData)
  }

  findFishById(id: string){
    return this.http.get(`${this.baseUrl}/fish/${id}`)
  }
}
