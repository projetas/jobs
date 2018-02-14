import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { DateValueAccessorModule } from 'angular-date-value-accessor';
import {Ng2MaskModule} from 'ng2-mask'
import { OnlyNumberDirective } from './shared/directives/onlynumber.directive';
import { CurrencyMaskModule } from "ng2-currency-mask";

import { AppComponent } from './app.component';
import { HomePageComponent } from './register/home-page/home-page.component';
import { FooterComponent } from './shared/footer/footer.component';
import { HeadbarComponent } from './shared/headbar/headbar.component';
import { SubMenuComponent } from './shared/sub-menu/sub-menu.component';
import { AppRoutingModule } from '../routing';


import { VehicleComponent } from './register/vehicles/vehicle.component';
import { VehicleListComponent } from './register/vehicles/vehicle-list/vehicle-list.component';



@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent,
    FooterComponent,
    HeadbarComponent,
    SubMenuComponent,
    VehicleComponent,
    OnlyNumberDirective,
    VehicleListComponent
    
  ],
  imports: [
    Ng2MaskModule,
    CurrencyMaskModule,
    RouterModule,
    BrowserModule,
    HttpModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
