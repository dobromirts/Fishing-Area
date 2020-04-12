import { RouterModule,Routes } from '@angular/router';
import { CreateComponent } from './create/create.component';
import { MapComponent } from '../map/map.component';
import { DetailComponent } from './detail/detail.component';
import { ListComponent } from './list/list.component';
import { AdminGuard } from '../user/admin.guard';

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
        component: CreateComponent,
        canActivate: [AdminGuard]
      },
      {
        path: 'all',
        component: ListComponent,
        canActivate: [AdminGuard]
      },
      {
        path: 'details/:name',
        component: DetailComponent
      }
    ]
  }
  ];
  
  export const RegionsRoutingModule = RouterModule.forChild(routes)