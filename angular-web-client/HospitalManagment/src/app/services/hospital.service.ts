import { Injectable } from '@angular/core';
import { Http, Request, RequestOptions, RequestMethod } from '@angular/http';
import { Hospital } from '../models/hospital.model';

@Injectable({
  providedIn: 'root'
})
export class HospitalService {

  constructor(public http:Http) { }

  addHospital(hospital: Hospital){
    return this.http.post('/hospital/add', hospital);
  }
}
