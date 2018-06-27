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

  constructor(public loginService:LoginService, public router: Router) { }

  ngOnInit() {
  }

  doLogout(){
    this.loginService.doLogout()
    .subscribe((item) => {
      let dataFromServer = JSON.parse(item._body);
      if(dataFromServer.responseType == 'SUCCESS'){
        localStorage.setItem('token', null);
        this.router.navigate(['']);
      }
    });
  }
}
