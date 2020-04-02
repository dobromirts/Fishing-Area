import { RouterModule,Routes } from '@angular/router';
import { CreateComponent } from './create/create.component';
import { MapComponent } from '../map/map.component';
import { DetailComponent } from './detail/detail.component';
import { ListComponent } from './list/list.component';

const routes: Routes = [
  {
    path: 'regions',
    children: [
      {
        path: '',
        pathMatch: 'full',
        redirectTo: '/map'
      },
      {
        path: 'create',
        component: CreateComponent
      },
      {
        path: 'all',
        component: ListComponent
      },
      {
        path: 'details/:name',
        component: DetailComponent
      }
    ]
  }
  ];
  
  export const RegionsRoutingModule = RouterModule.forChild(routes)