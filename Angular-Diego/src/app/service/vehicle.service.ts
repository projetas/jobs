import { Vehicle } from './../models/vehicle';
import { Observable } from 'rxjs';
import { BlockUIService } from './blockui.service';
import { Router } from '@angular/router';
import { Http } from '@angular/http';
import { BaseService } from './base.service';
import { Injectable } from '@angular/core';

@Injectable()
export class VehicleService extends BaseService {

    constructor(protected http: Http, protected router: Router, protected blockUIService: BlockUIService) { 
        super(http, router, blockUIService)
    }
    
    find(filtro: any): Observable<Array<Vehicle>> {
        return this.getList<Vehicle>('/find', filtro);
    }

    save(vehicle): Observable<any> {
        return this.persist('', vehicle);
    }

    remove(vehicle) {
        this.delete('/' + vehicle.id);
    }

    findById(id: any): Observable<Vehicle> {
        return this.getOne('/' + id);
    }
}