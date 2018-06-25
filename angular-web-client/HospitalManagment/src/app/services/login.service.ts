import { Injectable } from '@angular/core';
import { Http, Request, RequestOptions, RequestMethod } from '@angular/http';
import { LoginModel } from '../models/login.model';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(public http: Http) {
  }

  doLogin(loginModel: LoginModel){
    return this.http.post('/user/doLogin', loginModel);
  }
}
