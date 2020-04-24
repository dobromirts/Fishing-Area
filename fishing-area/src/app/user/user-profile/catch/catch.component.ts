import { Component, OnInit } from '@angular/core';
import { CatchAddModel } from './catch-add.model';
import { FishpondService } from 'src/app/fishpond/fishpond.service';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { AuthService } from '../../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-catch',
  templateUrl: './catch.component.html',
  styleUrls: ['./catch.component.scss','../../../../error-styles.scss']
})
export class CatchComponent implements OnInit {
  catch: CatchAddModel;
  pictureFile: File;
  file: any;
  userId: string
  errorOccurred=false;
  get fishponds(){
    return this.fishpondService.fishponds;
  }
  constructor(private fishpondService:FishpondService,private http:HttpClient,private authService:AuthService,private router:Router) { }

  ngOnInit() {
    this.catch=new CatchAddModel;
    this.fishpondService.allFishponds().subscribe();
    this.userId=this.authService.userId;
  }

  addCatch(){
    const formData = new FormData();
    const catchModel = new Blob([JSON.stringify(this.catch)], {type: 'application/json'});

    formData.append('catchModel', catchModel);
    formData.append('file', this.pictureFile);

    this.postData(formData)
      .subscribe((data: CatchAddModel) => {
        this.router.navigate(['/profile'])
      },(error: HttpErrorResponse) => {
        this.errorOccurred = true;
    });
  }

  postData(formData: FormData){
    return this.http.post(`http://localhost:8080/api/users/catch/${this.userId}`,formData)
  }


  handleFileInput(files: FileList, chooseFileLabel: HTMLLabelElement) {

    const file = files[0];
    const pattern = /image-*/;
    if (file && file.type.match(pattern)) {
      this.pictureFile = file;
      chooseFileLabel.innerHTML = this.pictureFile.name;
    } else {
      this.file = undefined;
      this.pictureFile = undefined;
      chooseFileLabel.innerHTML = 'Choose file';
    }
  }
}
