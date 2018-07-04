import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Head } from '../models/head.model';

@Injectable({
  providedIn: 'root'
})
export class HeadService {

  constructor(public http:Http) { 

  }

  createHead(head: Head){
    return this.http.post('/head/create', head);
  }
}
