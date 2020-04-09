import { RouterModule, Routes } from '@angular/router';
import { CreateComponent } from './create/create.component';
import { DetailsComponent } from './details/details.component';



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
                component: CreateComponent
            },
            {
                path: 'details/:id',
                component: DetailsComponent
            },
        ]
    }
];

export const FishRoutingModule = RouterModule.forChild(routes)