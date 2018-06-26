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
        this.router.navigate(['dashboard']);
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
    this.localStorageService.set('token', dataFromServer.responseData.authToken);
    this.localStorageService.set('entitledModules', dataFromServer.responseData.entitledModules);
    this.localStorageService.set('message', dataFromServer.responseData.message);
    this.localStorageService.set('userCode', dataFromServer.responseData.userCode);
    this.localStorageService.set('userName', dataFromServer.responseData.userName);
  }
}
