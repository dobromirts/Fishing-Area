import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/user/auth.service';
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
  ) { }

  ngOnInit() {
    this.authService.user.subscribe(user => {
      this.isUserLoggedIn = !!user;
      this.userRole = user ? user.role : undefined;
    });
  }

  onLogout() {
    this.authService.logout();
}

  

}
