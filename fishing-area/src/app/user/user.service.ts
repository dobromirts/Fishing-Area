import { Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http'
import { IUser } from '../shared/interfaces/user';
import {tap} from 'rxjs/operators'
import { AuthService } from '../auth/auth.service';
import { BehaviorSubject, Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class UserService{
  private basicUrl = 'http://localhost:8080'
  loggedUser = new BehaviorSubject<IUser>(null);
  loggedUserId: string;

  constructor(private http: HttpClient,private authService:AuthService) { 

  }
  getLoggedInUserObservable(): Observable<IUser> {
    return this.loggedUser;
  }

  loadLoggedUser() {
    this.authService.user.subscribe(user => {
      if (user) {
        this.loggedUserId = user.userId;
        this.getUserById(user.userId).subscribe(data => {
          this.loggedUser.next(data);
        });
      }
    });
  }

  getUserById(id: string): Observable<IUser> {
    return this.http.get<IUser>(`${this.basicUrl}/api/users/id/` + id);
  }

  getUserByUsername(username: string): Observable<IUser> {
    return this.http.get<IUser>(`${this.basicUrl}/api/users/username/` + username);
  }
  
}
