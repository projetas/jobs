import { VehicleResolver } from './../../resolvers/vehicle.resolver';
import { VehicleNewComponent } from './new/vehicle-new.component';

import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { VehicleComponent } from './vehicle.component';

const routes: Routes = [
  { path: '', component: VehicleComponent, data: { title: 'vehicle' } },
  { path: 'new', component: VehicleNewComponent },
  { path: 'edit/:id', component: VehicleNewComponent, resolve: { vehicle: VehicleResolver } }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class VehicleRoutingModule {}
