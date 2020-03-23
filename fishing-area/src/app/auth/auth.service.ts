import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, BehaviorSubject } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { IUser } from '../shared/interfaces/user';
import { UserAuthModel } from './userAuthModel';
import {SignInModel} from '../user/login/singInModel'
import { RegisterModel } from '../user/register/registerModel';

export interface AuthResponseData {
  kind: string;
  idToken: string;
  email: string;
  refreshToken: string;
  expiresIn: string;
  localId: string;
  registered?: boolean;
}


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private basicUrl = 'http://localhost:8080'

  user = new BehaviorSubject<UserAuthModel>(null);
  private tokenExpirationTimer: any;

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
    this.router.navigate(['/']);
    localStorage.removeItem('userData');
    // localStorage.removeItem('fp');
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
    delete localStorageUser.role;

    localStorage.setItem('userData', JSON.stringify(localStorageUser));
  }
}