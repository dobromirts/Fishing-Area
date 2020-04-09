import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {map, take} from 'rxjs/operators';

import {AuthService} from './auth.service';

@Injectable({providedIn: 'root'})
export class AdminGuard implements CanActivate {
    constructor(private authService: AuthService, private router: Router) {
    }

    canActivate(
        route: ActivatedRouteSnapshot,
        router: RouterStateSnapshot
    ):
        | boolean
        | UrlTree
        | Promise<boolean | UrlTree>
        | Observable<boolean | UrlTree> {
        return this.authService.user.pipe(
            take(1),
            map(user => {

                const isAuth = !!user;
                if (!isAuth) {
                    return this.router.createUrlTree(['/login']);
                }

                const hasNeededRole = user.role === 'ROLE_ADMIN' || user.role === 'ROLE_ROOT';
                if (hasNeededRole) {
                    return true;
                }
                return this.router.createUrlTree(['/']);
            })
        );
    }
}