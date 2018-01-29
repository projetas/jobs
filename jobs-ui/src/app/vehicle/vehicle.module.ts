import {VehicleService} from '../service/vehicle.service';
import {VehicleComponent} from './vehicle.component';
import {RouterModule, Routes} from '@angular/router';
import {MatCheckboxModule, MatIconModule, MatInputModule, MatPaginatorModule, MatRadioModule} from '@angular/material';
import {VehicleResolver} from './vehicle.resolver';
import {NgModule} from '@angular/core';
import {CommonModule, CurrencyPipe} from '@angular/common';
import {ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';

const routes: Routes = [
  {
    path: '',
    component: VehicleComponent,
  },
  {
    path: ':id',
    component: VehicleComponent,
    resolve: {
      vehicle: VehicleResolver
    }
  }
];

@NgModule({
  imports: [
    CommonModule,
    MatIconModule,
    MatRadioModule,
    MatInputModule,
    HttpClientModule,
    MatCheckboxModule,
    ReactiveFormsModule,
    MatPaginatorModule,
    RouterModule.forChild(routes)
  ],
  providers: [
    VehicleResolver,
    VehicleService,
    CurrencyPipe
  ],
  declarations: [
    VehicleComponent
  ]
})
export class VehicleModule {
}
