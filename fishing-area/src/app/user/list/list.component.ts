import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { IUser } from 'src/app/shared/interfaces/user';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs/operators';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class ListComponent implements OnInit {
  private basicUrl = 'http://localhost:8080'
  get users(){
    return this.authService.allUsers;
  }

  constructor(private authService:AuthService) { }

  ngOnInit() {
    this.authService.getAllUsers().subscribe();
  }
  
}
