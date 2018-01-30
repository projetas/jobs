import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from '@angular/router';
import {VehicleService} from '../service/vehicle.service';
import {Observable} from 'rxjs/Observable';
import {Injectable} from '@angular/core';
import {Vehicle} from '../model/vehicle';

@Injectable()
export class VehicleResolver implements Resolve<Vehicle> {

  constructor(private vehicleService: VehicleService) {

  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Vehicle>
    | Promise<Vehicle>
    | Vehicle {

    return this.vehicleService.getVehicle(route.params.id);

  }

}
