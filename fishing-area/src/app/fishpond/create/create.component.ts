import { Component, OnInit } from '@angular/core';
import { RegionsService } from 'src/app/regions/regions.service';
import { RegionModel } from 'src/app/regions/region-binding.model';
import { FishpondService } from '../fishpond.service';
import { Router } from '@angular/router';
import { FishpondAddModel } from './fishpond-add.model';

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.scss']
})
export class CreateComponent implements OnInit {
  fishpondAddModel: FishpondAddModel;
  pictureFile: File;
  file: any;

  get regions() {
    return this.regionsService.regions
  };

  constructor(private regionsService:RegionsService,private fishpondService: FishpondService,private router:Router) { }

  ngOnInit() {
    this.fishpondAddModel=new FishpondAddModel;
    this.regionsService.allRegions().subscribe();

  }

  addFishpond() {
    const formData = new FormData();
    const fishpond = new Blob([JSON.stringify(this.fishpondAddModel)], {type: 'application/json'});

    formData.append('fishpondAddModel', fishpond);
    formData.append('file', this.pictureFile);

    this.fishpondService.addFishopond(formData)
      .subscribe((fishpond: FishpondAddModel) => {
        // this.router.navigate(['/fishpond/details/' + fishpond.name])});
        this.router.navigate(['/'])
      },console.error);
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
