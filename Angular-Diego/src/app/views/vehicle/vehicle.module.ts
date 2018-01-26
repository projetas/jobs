import { VehicleRoutingModule } from './vehicle-routing.module';
import { VehicleResolver } from './../../resolvers/vehicle.resolver';
import { VehicleNewComponent } from './new/vehicle-new.component';
import { BlockUIService } from './../../service/blockui.service';
import { VehicleService } from './../../service/vehicle.service';
import { NgModule } from '@angular/core';
import { ChartsModule } from 'ng2-charts/ng2-charts';
import { CommonModule } from "@angular/common";
import { HttpModule } from "@angular/http";
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { VehicleComponent } from "app/views/vehicle/vehicle.component";
import { CurrencyMaskModule } from "ng2-currency-mask";

@NgModule({
  imports: [
    CommonModule,
    HttpModule,
    VehicleRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    CurrencyMaskModule
  ],
  declarations: [ VehicleComponent, VehicleNewComponent ],
  providers: [VehicleService, BlockUIService, VehicleResolver]
})
export class VehicleModule { }
