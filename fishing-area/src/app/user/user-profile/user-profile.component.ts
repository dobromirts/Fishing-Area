import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { IUser } from 'src/app/shared/interfaces/user';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {


  constructor(private router:Router,private authSerice:AuthService) { }

  ngOnInit() {
    this.authSerice.loadLoggedUser();
  }

}
