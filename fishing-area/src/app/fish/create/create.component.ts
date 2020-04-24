import { Component, OnInit } from '@angular/core';
import { FishAddModel } from './fish-add.model';
import { FishpondService } from 'src/app/fishpond/fishpond.service';
import { Router } from '@angular/router';
import { FishService } from '../fish.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.scss','../../../error-styles.scss']
})
export class CreateComponent implements OnInit {
  fish:FishAddModel;
  selectedFishponds: string[];
  pictureFile:File;
  file: any;
  errorOccurred=false;


  get fishponds(){
    return this.fishpondService.fishponds;
  }

  constructor(private fishService: FishService,private fishpondService:FishpondService,private router:Router) { }

  ngOnInit() {
    this.fish=new FishAddModel;
    this.fishpondService.allFishponds().subscribe();
    
  }

  addFish() {
    this.fish.fishponds=this.selectedFishponds;
    const formData = new FormData();
    const fishpond = new Blob([JSON.stringify(this.fish)], {type: 'application/json'});

    formData.append('fishAddModel', fishpond);
    formData.append('file', this.pictureFile);

    this.fishService.addFish(formData)
      .subscribe((fish: FishAddModel) => {
        // this.router.navigate(['/fishpond/details/' + fishpond.name])});
        this.router.navigate(['/'])
      },(error: HttpErrorResponse) => {
        this.errorOccurred = true;
    });
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

  clickedOption(){
    console.log(this.selectedFishponds)
  }

}
