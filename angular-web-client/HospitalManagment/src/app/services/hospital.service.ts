import { Injectable } from '@angular/core';
import { Http, Request, RequestOptions, RequestMethod } from '@angular/http';
import { Hospital } from '../models/hospital.model';
import { LocalStorageService } from 'angular-2-local-storage';

@Injectable({
  providedIn: 'root'
})
export class HospitalService {

  constructor(public http:Http, public localStorageService: LocalStorageService) { }

  addHospital(hospital: Hospital){
    let options:any = new RequestOptions();
    options.headers.append('X-Auth-Token', this.localStorageService.get('token'));
    return this.http.post('/hospital/add', hospital, options);
  }
}
