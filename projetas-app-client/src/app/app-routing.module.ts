import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from './security/guards/auth.guard';
import { Role } from './security/role.enum';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { VehiclesComponent } from './vehicles/vehicles.component';
import { VehicleFormComponent } from './vehicles/vehicle-form/vehicle-form.component';
import { VehicleDetailsComponent } from './vehicles/vehicle-details/vehicle-details.component';

const routes: Routes = [
   { path: 'login',                       component: LoginComponent },
   { path: 'signup',                      component: RegisterComponent },
   { path: '',                            component: HomeComponent,           canActivate: [AuthGuard] },
   { path: 'vehicles',                    component: VehiclesComponent,       canActivate: [AuthGuard], data: { roles: [Role.Admin, Role.User] }},
   { path: 'vehicle/new',                 component: VehicleFormComponent,    canActivate: [AuthGuard], data: { roles: [Role.Admin] }}, 
   { path: 'vehicle/edit/:vehicleId',     component: VehicleFormComponent,    canActivate: [AuthGuard], data: { roles: [Role.Admin] }},
   { path: 'vehicle/details/:vehicleId',  component: VehicleDetailsComponent, canActivate: [AuthGuard], data: { roles: [Role.Admin, Role.User] }},
   { path: '**', redirectTo: '' }
];

@NgModule({
   imports: [RouterModule.forRoot(routes)],
   exports: [RouterModule]
})
export class AppRoutingModule { }
