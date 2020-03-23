import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/auth/auth.service';
import { UserService } from 'src/app/user/user.service';
import { Subscription } from 'rxjs';
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit{

  isUserLoggedIn = false;
  userRole: string;

  constructor(
    private authService: AuthService,
    private userService: UserService
  ) { }

  ngOnInit() {
    this.authService.user.subscribe(user => {
      this.isUserLoggedIn = !!user;
      this.userRole = user ? user.role : undefined;
    });
  }

  onLogout() {
    this.authService.logout();
    this.userService.loggedUser.next(null);
}

  

}
