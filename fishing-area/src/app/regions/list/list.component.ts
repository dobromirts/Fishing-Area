import { Component, OnInit, OnChanges } from '@angular/core';
import { RegionsService } from '../regions.service';
import { RegionModel } from '../region-binding.model';
import { pipe } from 'rxjs';


@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class ListComponent implements OnInit {
  get regions() {
    return this.regionsService.regions
  };

  constructor(private regionsService: RegionsService) { }

  ngOnInit() {
    this.regionsService.allRegions().subscribe()
  }









}
