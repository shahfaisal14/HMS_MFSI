import { Component, OnInit } from '@angular/core';
import { LoginModel } from '../../models/login.model';
import { LoginService } from '../../services/login.service';
import { Overlay } from 'ngx-modialog';
import { Modal } from 'ngx-modialog/plugins/bootstrap';
import { LocalStorageService } from 'angular-2-local-storage';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.less']
})
export class LoginComponent implements OnInit {

  constructor(private loginService: LoginService, private modal: Modal, private localStorageService: LocalStorageService, private router:Router) { 
    
  }

  private login: LoginModel = new LoginModel(null, null);

  private loginStep: string = 'login';

  ngOnInit() {
  }

  doLogin(){
    this.loginService.doLogin(this.login)
    .subscribe((item) => {
      let dataFromServer = JSON.parse(item._body);
      if(dataFromServer.responseType == 'SUCCESS'){
        this.setLoginEssentials(dataFromServer);
        this.router.navigate(['admin/dashboard']);
      }
    });
  }

  forgotPassword(){
    this.loginStep = 'forgotPassword';
    this.login.passCode = '';
  }

  backToLogin(){
    this.loginStep = 'login';
  }

  requestPasswordReset(){
    this.loginService.requestPasswordReset(this.login.userCode)
    .subscribe((item) => {
      let dataFromServer = JSON.parse(item._body);
      if(dataFromServer.responseType == 'SUCCESS'){
        alert(dataFromServer.responseData.message);
        this.loginStep = 'login';
      }
    });
  }

  private setLoginEssentials(dataFromServer: any){
    localStorage.setItem('token', dataFromServer.responseData.authToken);
    localStorage.setItem('entitledModules', dataFromServer.responseData.entitledModules);
    localStorage.setItem('message', dataFromServer.responseData.message);
    localStorage.setItem('userCode', dataFromServer.responseData.userCode);
    localStorage.setItem('userName', dataFromServer.responseData.userName);
    localStorage.setItem('userRole', dataFromServer.responseData.userRole);
  }
}
