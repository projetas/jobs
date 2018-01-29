import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {PageQuery} from '../model/page-query';
import {Observable} from 'rxjs/Observable';
import {Injectable} from '@angular/core';
import {Vehicle} from '../model/vehicle';

@Injectable()
export class VehicleService {

  constructor(protected http: HttpClient) {
  }

  getVehicles(criteria: HttpParams = new HttpParams()): Observable<PageQuery<Vehicle>> {

    const headers = new HttpHeaders()
      .set('Accept', 'application/json')
      .set('Content-Type', 'application/x-www-form-urlencoded');

    const options = {
      headers: headers,
      params: criteria
    };

    return this.http.get<PageQuery<Vehicle>>(environment.api + '/v1/vehicles', options);

  }

  getVehicle(id: string): Observable<Vehicle> {

    const headers = new HttpHeaders()
      .set('Accept', 'application/json')
      .set('Content-Type', 'application/x-www-form-urlencoded');

    return this.http.get<Vehicle>(environment.api + '/v1/vehicles/'.concat(id), {
      headers: headers,
    });

  }

  saveVehicle(vehicle: Vehicle): Observable<Vehicle> {

    const headers = new HttpHeaders()
      .set('Accept', 'application/json')
      .set('Content-Type', 'application/json');

    return this.http.post<Vehicle>(environment.api + '/v1/vehicles', vehicle, {
      headers: headers,
    });

  }

  updateVehicle(vehicle: Vehicle): Observable<Vehicle> {

    const headers = new HttpHeaders()
      .set('Accept', 'application/json')
      .set('Content-Type', 'application/json');

    return this.http.put<Vehicle>(environment.api + '/v1/vehicles', vehicle, {
      headers: headers,
    });

  }

  removeVehicle(id: string): Observable<void> {

    const headers = new HttpHeaders()
      .set('Accept', 'application/json')
      .set('Content-Type', 'application/x-www-form-urlencoded');

    return this.http.delete<void>(environment.api + '/v1/vehicles/'.concat(id), {
      headers: headers,
    });

  }

}
