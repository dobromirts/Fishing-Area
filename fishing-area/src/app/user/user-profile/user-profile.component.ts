import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { IUser } from 'src/app/shared/interfaces/user';
import { AuthService } from '../auth.service';
import { UserProfile } from './user-profile.model';
import { HttpClient } from '@angular/common/http';
import { CatchAddModel } from './catch/catch-add.model';
import { tap } from 'rxjs/operators';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {

  userProfile: UserProfile;
  catches: CatchAddModel[];

  constructor(private router:Router,private authSerice:AuthService) { }

  ngOnInit() {
    this.authSerice.getUserProfile().subscribe((profile:UserProfile)=>{
      this.userProfile=profile;
      console.log(this.userProfile)
    });
    this.authSerice.getCatches().subscribe((data:CatchAddModel[])=>{
      this.catches=[].concat(data)
      console.log(this.catches)
    })
  }

  

}
