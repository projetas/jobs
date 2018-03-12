import { Http, Headers } from '@angular/http';
import { Injectable } from '@angular/core';

import 'rxjs/add/operator/toPromise';
import { Car } from './cars-create/model';
import { environment } from '../environments/environment';

@Injectable()
export class CarService {

  carsUrl: string;


  constructor(private http: Http) {
    this.carsUrl = `${environment.apiUrl}/cars`;
   }

  pesquisar(): Promise<any> {
      // const headers = new Headers();
      // headers.append('Authorization', 'Basic YWRtaW5AYWxnYW1vbmV5LmNvbTphZG1pbg==');

     return this.http.get(this.carsUrl)
      .toPromise()
      .then(response => response.json());
  }

  excluir(codigo: number): Promise<void> {
    // const headers = new Headers();
    // headers.append('Authorization', 'Basic YWRtaW5AYWxnYW1vbmV5LmNvbTphZG1pbg==');

    return this.http.delete(`${this.carsUrl}/${codigo}`)
      .toPromise()
      .then(() => null);
  }

  adicionar(car: Car): Promise<Car> {

    const headers = new Headers();
    // headers.append('Authorization', 'Basic YWRtaW5AYWxnYW1vbmV5LmNvbTphZG1pbg==');
    headers.append('Content-Type', 'application/json');

    return this.http.post(this.carsUrl,
      JSON.stringify(car), { headers })
    .toPromise()
    .then(response => response.json());
  }

}
