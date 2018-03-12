import { CarService } from './car.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Routes, RouterModule } from '@angular/router';
import { ToastyModule} from 'ng2-toasty';

import { InputTextModule } from 'primeng/components/inputtext/inputtext';
import { ButtonModule } from 'primeng/components/button/button';
import { DataTableModule } from 'primeng/components/datatable/datatable';
import { CalendarModule } from 'primeng/components/calendar/calendar';
import { DropdownModule } from 'primeng/components/dropdown/dropdown';

import { CurrencyMaskModule } from 'ng2-currency-mask';


import { AppComponent } from './app.component';
import { CarsSearchComponent } from './cars-search/cars-search.component';
import { CarsCreateComponent } from './cars-create/cars-create.component';
import { HttpModule } from '@angular/http';

const routes: Routes = [
  { path: 'cars', component: CarsSearchComponent },
  { path: 'cars/novo', component: CarsCreateComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    CarsSearchComponent,
    CarsCreateComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    InputTextModule,
    BrowserAnimationsModule,
    ButtonModule,
    DataTableModule,
    CalendarModule,
    CurrencyMaskModule,
    DropdownModule,
    HttpModule,
    RouterModule.forRoot(routes),
    ToastyModule.forRoot()
  ],
  providers: [CarService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
