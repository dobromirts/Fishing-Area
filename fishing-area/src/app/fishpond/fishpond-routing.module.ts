import { RouterModule,Routes } from '@angular/router';
import { CreateComponent } from './create/create.component';
import {ListComponent} from './list/list.component'
import { DetailsComponent } from './details/details.component';


const routes: Routes = [
  {
    path: 'fishpond',
    children: [
      {
        path: '',
        pathMatch: 'full',
        redirectTo: '/fishpond/create'
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
        path: 'details/:id',
        component: DetailsComponent
      },
    ]
  }
  ];
  
  export const FishpondRoutingModule = RouterModule.forChild(routes)