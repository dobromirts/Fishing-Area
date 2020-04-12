import { RouterModule, Routes } from '@angular/router';
import { CreateComponent } from './create/create.component';
import { DetailsComponent } from './details/details.component';
import { AdminGuard } from '../user/admin.guard';



const routes: Routes = [
    {
        path: 'fish',
        children: [
            {
                path: '',
                pathMatch: 'full',
                redirectTo: '/fish/create'
            },
            {
                path: 'create',
                component: CreateComponent,
                canActivate: [AdminGuard]
            },
            {
                path: 'details/:id',
                component: DetailsComponent
            },
        ]
    }
];

export const FishRoutingModule = RouterModule.forChild(routes)