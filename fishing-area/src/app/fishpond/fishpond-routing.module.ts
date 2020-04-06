import { RouterModule,Routes } from '@angular/router';
import { CreateComponent } from './create/create.component';
import {ListComponent} from './list/list.component'


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
    ]
  }
  ];
  
  export const FishpondRoutingModule = RouterModule.forChild(routes)