import { Component, OnInit } from '@angular/core';
import { IUser } from '../shared/interfaces/user';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  users: IUser[];

  constructor() { }
  images = [944, 1011, 984].map((n) => `https://picsum.photos/id/${n}/1800/450`);
  

  ngOnInit() {
    // this.loadUsers();
  }

  // loadUsers(){
  //   this.userService.getUsers().subscribe(data=>this.users=data)
  // }

}
