import { RouterModule,Routes } from '@angular/router';
import { CreateComponent } from './create/create.component';
import {ListComponent} from './list/list.component'
import { DetailsComponent } from './details/details.component';
import { EditComponent } from './edit/edit.component';
import { AdminGuard } from '../user/admin.guard';


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
        component: CreateComponent,
        canActivate: [AdminGuard]
      },
      {
        path: 'all',
        component: ListComponent
      },
      {
        path: 'details/:id',
        component: DetailsComponent
      },
      {
        path: 'edit/:id',
        component: EditComponent,
        canActivate: [AdminGuard]
      }
    ]
  }
  ];
  
  export const FishpondRoutingModule = RouterModule.forChild(routes)