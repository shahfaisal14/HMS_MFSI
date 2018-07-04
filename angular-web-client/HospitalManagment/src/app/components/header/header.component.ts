import { Component, OnInit } from '@angular/core';
import { LoginService } from '../../services/login.service';
import { LocalStorageModule, LocalStorageService } from 'angular-2-local-storage';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.less']
})
export class HeaderComponent implements OnInit {

  private loggedInUserFullName: string;
  private headerTitle: string;

  constructor(public loginService:LoginService, public router: Router) { }

  ngOnInit() {
    let authToken = localStorage.getItem('token');
    if(authToken == null || authToken == undefined || authToken == '' || authToken == "null" || authToken == "undefined"){
      this.router.navigate(['']);
    } else {
      this.loggedInUserFullName = localStorage.getItem('userName');
      let currentUrl = this.router.url;
      if(currentUrl == '/admin/dashboard'){
        this.headerTitle = "Dashboard";
      }
      if(currentUrl == '/hospitals'){
        this.headerTitle = "Hospitals List";
      }
      if(currentUrl == '/doctors'){
        this.headerTitle = "Doctors List";
      }
      if(currentUrl == '/hospital/new'){
        this.headerTitle = "New Hospital";
      }
    }
  }

  doLogout(){
    this.loginService.doLogout()
    .subscribe((item) => {
      let dataFromServer = JSON.parse(item._body);
      if(dataFromServer.responseType == 'SUCCESS'){
        this.unsetLoginEssentials();
        this.router.navigate(['']);
      }
    });
  }

  private unsetLoginEssentials(){
    localStorage.setItem('token', null);
    localStorage.setItem('entitledModules', null);
    localStorage.setItem('message', null);
    localStorage.setItem('userCode', null);
    localStorage.setItem('userName', null);
    localStorage.setItem('userRole', null);
  }
}
