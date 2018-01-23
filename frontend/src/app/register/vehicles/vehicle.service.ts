import { Injectable } from "@angular/core";

import { Http, Headers, Response, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import { environment } from "../../../environments/environment";

@Injectable()
export class VehicleService {

    constructor(private http: Http) { }
    private url = environment.TESTE_API + environment.VEHICLES_ENDPOINT;

    getVehicle(){
        return this.http
        .get(this.url)
        .map(this.extractData);
    }

    getVehicleById(id: number){
        return this.http
        .get(this.url + '/' + id)
        .map((res: Response) => res.json());
    }
    private extractData(res: Response) {        
        return res.text() ? res.json() : {}; ;
    }

    postVehicle(data: any) {
        let headers = new Headers(); 
        headers.append('Content-Type', 'application/json');
        let dataJson =  JSON.stringify(data);
        return this.http
            .post(this.url, dataJson, { headers: headers})
            .map(this.extractData);
    }

    updateVehicle(data: any) {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(data);
        return this.http.put(this.url, body, options ).
         map(this.extractData);
      }

      deleteVehicle(id: number){
        return this.http
        .delete(this.url + '/' + id)
        .map(this.extractData);
      }
}