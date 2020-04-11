import { Component, OnInit } from '@angular/core';
import { EditProfileModel } from './edit-profile.model';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { AuthService } from '../../auth.service';

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.scss']
})
export class EditComponent implements OnInit {
  editProfileModel: EditProfileModel;
  pictureFile: File;
  file: any;
  userId: string
  constructor(private router:Router,private http: HttpClient,private authService:AuthService) { }

  ngOnInit() {
    this.editProfileModel=new EditProfileModel;
    this.userId=this.authService.userId;
  }

  editProfile(){
    const formData = new FormData();
    const profile = new Blob([JSON.stringify(this.editProfileModel)], {type: 'application/json'});

    formData.append('profile', profile);
    formData.append('file', this.pictureFile);

    this.addProfile(formData)
      .subscribe((profile: EditProfileModel) => {
        this.router.navigate(['/profile'])
      },console.error);
  }

  addProfile(formData: FormData){
    return this.http.post(`http://localhost:8080/api/users/profile/${this.userId}`,formData)
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
