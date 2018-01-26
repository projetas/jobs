import { ResponseError } from './../models/response-error';
import { BlockUIService } from './blockui.service';
import { environment } from './../../environments/environment';
import { Injectable, Inject, enableProdMode, ElementRef } from '@angular/core';
import { Observable } from 'rxjs';
import { Http, Headers, Response, ResponseContentType, RequestMethod, RequestOptions } from '@angular/http';
import { Router } from '@angular/router';
import 'rxjs/add/operator/map'

@Injectable()
export class BaseService {

    private headers: Headers;

    private baseUrl = 'http://localhost:8081/vehicle';

    constructor(protected http: Http, protected router: Router, protected blockUIService: BlockUIService) {
        this.headers = new Headers();
        this.headers.append('content-type', 'application/json');
        if (environment.production) {
            this.baseUrl = '/vehicle';
        }
    }

    getAll<T>(url: string): Observable<T[]> {

        this.blockUIService.event.emit({
            value: true
        });

        this.headers = new Headers();
        this.headers.append('content-type', 'application/json');

        const options = new RequestOptions({ headers: this.headers });

        return this.http.get(this.baseUrl, options)
            .map((response: Response) => {
                return this.extractData(response);
            }).catch((error: Response | any) =>
                this.handleError(error)
            );
    }

    getList<T>(url: string, json?: any): Observable<Array<T>> {

        this.blockUIService.event.emit({
            value: true
        });

        this.headers = new Headers();

        if (json) {

            this.headers.append('Content-Type', 'application/json');
            const options = new RequestOptions({ headers: this.headers });

            return this.http.post(this.baseUrl + url, JSON.stringify(json), options)
                .map((response: Response) => {
                    return this.extractData(response);
                })
        }

        this.headers.append('Content-Type', 'application/json');
        const options = new RequestOptions({ headers: this.headers });

        return this.http.get(this.baseUrl + url, options)
            .map((response: Response) => {
                return this.extractData(response);
            })
    }

    getOne<T>(url: String): Observable<T> {

        this.blockUIService.event.emit({
            value: true
        });

        this.headers = new Headers();
        this.headers.append('Content-Type', 'application/x-www-form-urlencoded');

        const options = new RequestOptions({ headers: this.headers });

        return this.http.get(this.baseUrl + url, options)
            .map((response: Response) => {
                return this.extractData(response);
            });
    }

    getOnePost<T>(url: String, data: any): Observable<T> {

        this.blockUIService.event.emit({
            value: true
        });

        this.headers = new Headers();
        this.headers.append('Content-Type', 'application/json');

        const options = new RequestOptions({ headers: this.headers });

        return this.http.post(this.baseUrl + url, JSON.stringify(data), options)
            .map((response: Response) => {
                return this.extractData(response);
            });
    }

    persist<T>(url: string, data: T): Observable<T> {

        this.blockUIService.event.emit({
            value: true
        });

        this.headers = new Headers();
        this.headers.append('Content-Type', 'application/json');

        const options = new RequestOptions({ headers: this.headers });

        return this.http.post(this.baseUrl + url, JSON.stringify(data), options)
            .map((response: Response) => {
                return this.extractData(response);
            });

    }

    delete<T>(url: String): Observable<T> {

        this.blockUIService.event.emit({
            value: true
        });

        this.headers = new Headers();
        this.headers.append('Content-Type', 'application/x-www-form-urlencoded');

        const options = new RequestOptions({ headers: this.headers });

        return this.http.delete(this.baseUrl + url, options)
            .map((response: Response) => {
                return this.extractData(response);
            });
    }

    private extractData(res: Response) {

        this.blockUIService.event.emit({
            value: false
        });

        return res.json();

    }

    private handleError(error: Response | any) {

        this.blockUIService.event.emit({
            value: false
        });

        let err: ResponseError = new ResponseError();

        if (error instanceof Response) {

            if (error.status === 401) {
                this.router.navigate(['login']);
                localStorage.removeItem('currentUser');
            }

            if (error.status === 403) {
                this.router.navigate(['/acessonegado']);
            }

            if (error.status === 412) {
                this.router.navigate(['/dadosinsatisfatorios']);
            }

            if (error.status >= 500) {
                this.router.navigate(['/error']);
            }

            err = error.json();

        }

        return Observable.throw(err);

    }

}
