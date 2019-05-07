import { NgModule, LOCALE_ID  } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule, HTTP_INTERCEPTORS } from "@angular/common/http";
import { registerLocaleData } from '@angular/common';
import ptBr from '@angular/common/locales/pt';

import { AppComponent } from './app.component';
import { JwtInterceptor } from './security/interceptors/jwt.interceptor';
import { ErrorInterceptor } from './security/interceptors/error.interceptor';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { VehiclesComponent } from './vehicles/vehicles.component';
import { VehicleFormComponent } from './vehicles/vehicle-form/vehicle-form.component';
import { VehicleDetailsComponent } from './vehicles/vehicle-details/vehicle-details.component';
import { VehicleService } from './services/vehicle.service';

registerLocaleData(ptBr)

@NgModule({
	declarations: [
		AppComponent,
		LoginComponent,
		RegisterComponent,
		HomeComponent,
		VehiclesComponent,
		VehicleFormComponent,
		VehicleDetailsComponent
	],
	imports: [
		BrowserModule,
		FormsModule,
		ReactiveFormsModule,
		HttpClientModule,
		AppRoutingModule
	],
	providers: [
		{ provide: LOCALE_ID, useValue: 'pt-BR' },
		{ provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
		{ provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
		VehicleService
	],
	bootstrap: [AppComponent]
})
export class AppModule { }
