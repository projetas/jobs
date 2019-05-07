import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { Vehicle } from '../models/vehicle.model';

@Injectable({
  providedIn: 'root'
})
export class VehicleService {

    private vehiclesUrl = "http://localhost:8080/api/vehicles";

    list: Vehicle[];

    constructor(private http: HttpClient) { }

    refreshList() {
        this.http.get<Vehicle[]>(this.vehiclesUrl)
            .toPromise()
            .then(res => this.list = res as Vehicle[]);
    }

    getById(id: number): Observable<Vehicle> {
        return this.http.get<Vehicle>(this.vehiclesUrl + '/' + id);
    }

    save(formData: Vehicle): any {
        return this.http.post(this.vehiclesUrl, formData);
    }

    update(formData: Vehicle): any {
        return this.http.put(this.vehiclesUrl + '/' + formData.id, formData);
    }

    delete(id: number): any {
        return this.http.delete(this.vehiclesUrl + '/' + id);
    }

}
