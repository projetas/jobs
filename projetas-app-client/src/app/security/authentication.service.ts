import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { JwtInfo } from './jwt-info';
import { AuthLoginInfo } from './login-info';
import { SignUpInfo } from './signup-info';

const TOKEN_KEY = 'AuthToken';

const httpOptions = {
   headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({ providedIn: 'root' })
export class AuthenticationService {

   private authUrl = "http://localhost:8080/api/auth/signin";
   private signupUrl = 'http://localhost:8080/api/auth/signup';
   
   private currentUserSubject: BehaviorSubject<JwtInfo>;
   public currentUser: Observable<JwtInfo>;

   constructor(private http: HttpClient) {
      this.currentUserSubject = new BehaviorSubject<JwtInfo>(JSON.parse(localStorage.getItem(TOKEN_KEY)));
      this.currentUser = this.currentUserSubject.asObservable();
   }

   public get currentUserValue(): JwtInfo {
      return this.currentUserSubject.value;
   }

   signUp(info: SignUpInfo): Observable<string> {
      return this.http.post<string>(this.signupUrl, info, httpOptions);
   }

   login(credentials: AuthLoginInfo) {
      return this.http.post<JwtInfo>(this.authUrl, credentials, httpOptions)
         .pipe(map(res => {
            // login successful if there's a jwt token in the response
            if (res && res.accessToken) {
               // store user details and jwt token in local storage to keep user logged in between page refreshes
               localStorage.setItem(TOKEN_KEY, JSON.stringify(res));
               this.currentUserSubject.next(res);
            }
            return res;
         }));
   }

   logout() {
      // remove user from local storage to log user out
      localStorage.removeItem(TOKEN_KEY);
      this.currentUserSubject.next(null);
   }
}