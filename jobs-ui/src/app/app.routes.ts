import {RouterModule, Routes} from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    loadChildren: './vehicle/vehicle.module#VehicleModule'
  },
  {path: '**', redirectTo: ''}
];

export const AppRoutes = RouterModule.forRoot(routes, {
  useHash: true
});
