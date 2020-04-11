import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, BehaviorSubject } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { IUser } from '../shared/interfaces/user';
import { UserAuthModel } from './user-auth.model';
import {SignInModel} from './login/signIn-binding.model'
import { RegisterModel } from './register/register-binding.model';
import { tap } from 'rxjs/operators';




@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private basicUrl = 'http://localhost:8080'

  user = new BehaviorSubject<UserAuthModel>(null);
  userId: string;
  loggedUser: IUser
  private tokenExpirationTimer: any;
  allUsers:IUser[];

  constructor(private http: HttpClient, private router: Router) { }

  register(user: RegisterModel) {
    return this.http.post(`${this.basicUrl}/api/users/register`, user)
  }

  signIn(singInModel: SignInModel) {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
  });
    return this.http.post(`${this.basicUrl}/api/users/signin`,singInModel,{
      headers: headers,
      observe: "response"
    })
  }

  logout() {
    this.user.next(null);
    this.userId=null;
    this.loggedUser=null
    this.router.navigate(['/']);
    localStorage.removeItem('userData');
    if (this.tokenExpirationTimer) {
      clearTimeout(this.tokenExpirationTimer);
    }
    this.tokenExpirationTimer = null;
  }

  autoLogout(expirationDuration: number) {
    this.tokenExpirationTimer = setTimeout(() => {
        this.logout();
    }, expirationDuration);
}


  isUserAdmin(userRole: string) {

    return userRole === 'ROLE_ROOT' || userRole === 'ROLE_ADMIN';

  }

  public handleAuthentication(token: string, rememberMe: boolean) {


    const payload = JSON.parse(atob((token.replace('Bearer: ', '').split('.')[1])));
    const userId = payload.userId;
    const userRole = payload.role;
    const tokenExpiresInMS = payload.exp;
    

    const expirationDate = new Date(new Date().getTime() + 863940000);


    const user = new UserAuthModel(userId, userRole, rememberMe, token, expirationDate);
    this.user.next(user);
    this.autoLogout(tokenExpiresInMS);
    

    const localStorageUser = { ...user };
    this.userId=localStorageUser.userId
    delete localStorageUser.role;
    localStorage.setItem('userData', JSON.stringify(localStorageUser));
  }



  loadLoggedUser() {
    if (this.userId) {
      this.getUserById(this.userId).subscribe(data => {
        this.loggedUser= data;
      });
      
    }
  }

  getUserById(id: string): Observable<IUser> {
    return this.http.get<IUser>(`${this.basicUrl}/api/users/id/` + id);
  }

  getUserByUsername(username: string): Observable<IUser> {
    return this.http.get<IUser>(`${this.basicUrl}/api/users/username/` + username);
  }

  getUserProfile(){
    return this.http.get(`${this.basicUrl}/api/users/profile/` + this.userId);
  }
  getCatches(){
    return this.http.get(`${this.basicUrl}/api/users/catch/` + this.userId);
  }

  checkIfUserExistsByUsername(username: string) {
    return this.http.get(`${this.basicUrl}/api/users/exists/` + username);
  }

  getAllUsers(){
    return this.http.get<IUser[]>(`${this.basicUrl}/api/users/all`).pipe(
      tap((users:IUser[])=>{
        this.allUsers=[].concat(users)
        
      })
    )
  }
  
}