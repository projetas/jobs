import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomePageComponent } from './app/register/home-page/home-page.component';
import { VehicleComponent } from './app/register/vehicles/vehicle.component';
import { VehicleListComponent } from './app/register/vehicles/vehicle-list/vehicle-list.component';



const appRoutes: Routes = [
    { path: '', redirectTo: 'vehicleList', pathMatch: 'full' },
    { path: 'vehicle/:id', pathMatch: 'full', component: VehicleComponent },
    { path: 'vehicle', pathMatch: 'full', component: VehicleComponent },
    { path: 'vehicleList', pathMatch: 'full', component: VehicleListComponent },
    { path: '**', redirectTo: 'vehicleList' },
    { path: 'home', pathMatch: 'full', component: HomePageComponent },

];

@NgModule({
    imports: [RouterModule.forRoot(appRoutes)],
    exports: []
  })
  
  export class AppRoutingModule { }