import { Injectable } from '@angular/core';
import { Http, Request, RequestOptions, RequestMethod } from '@angular/http';
import { LoginModel } from '../models/login.model';
import { LocalStorageService } from 'angular-2-local-storage';
import { HttpInterceptorService } from '../http/http.interceptor';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(public http: Http) {
  }

  doLogin(loginModel: LoginModel){
    return this.http.post('/user/doLogin', loginModel);
  }

  requestPasswordReset(userId:string){
    return this.http.post('/user/forgotPassword?userId='+userId, null)
  }

  doLogout(){
    return this.http.delete('/user/doLogout');
  }
}
