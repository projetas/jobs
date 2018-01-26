import { VehicleService } from './../service/vehicle.service';
import { Vehicle } from './../models/vehicle';

import { Observable } from 'rxjs';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Injectable } from '@angular/core';

@Injectable()
export class VehicleResolver implements Resolve<Vehicle> {

    constructor(private vehicleService: VehicleService) {
    }

    resolve(
        route: ActivatedRouteSnapshot, 
        state: RouterStateSnapshot): Observable<Vehicle> {
            return this.vehicleService.findById(route.params['id']);
        }
}