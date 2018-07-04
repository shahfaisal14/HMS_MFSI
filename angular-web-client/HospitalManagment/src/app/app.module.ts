import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule, Http, XHRBackend, RequestOptions } from '@angular/http';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';

import { LoginService } from './services/login.service';
import { HospitalService } from './services/hospital.service';

import { httpFactory } from './http/http.factory';


import { ModalModule } from 'ngx-modialog';
import { BootstrapModalModule } from 'ngx-modialog/plugins/bootstrap';

import { LocalStorageModule } from 'angular-2-local-storage';
import { HeaderComponent } from './components/header/header.component';
import { LeftNavComponent } from './components/left-nav/left-nav.component';
import { ContentPanelComponent } from './components/content-panel/content-panel.component';

// ag Grid
import { AgGridModule } from 'ag-grid-angular';

// Draggable Grid
import { NgGridModule } from 'angular2-grid';

// Import FusionCharts core module
import * as FusionCharts from 'fusioncharts';
import * as Charts from 'fusioncharts/fusioncharts.charts';
import * as FintTheme from 'fusioncharts/themes/fusioncharts.theme.fint';
import { FusionChartsModule } from 'angular4-fusioncharts';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { AddHospitalComponent } from './components/add-hospital/add-hospital.component';
import { HttpInterceptorService } from './http/http.interceptor';
import { HospitalListingComponent } from './components/hospital-listing/hospital-listing.component';
import { CreateHeadComponent } from './components/create-head/create-head.component';
import { DoctorsListingComponent } from './components/doctors-listing/doctors-listing.component';
import { HeadService } from './services/head.service';


// Pass it to fcRoot method to resolve module dependencies
// Pass FusionCharts core as first argument.
FusionChartsModule.fcRoot(FusionCharts, Charts, FintTheme);

const appRoutes: Routes = [
    {
      path      : '',
      component : LoginComponent
    },
    {
      path      : 'admin/dashboard',
      component : DashboardComponent
    },
    {
      path      : 'hospital/new',
      component : AddHospitalComponent
    },
    {
      path      : 'hospitals',
      component : HospitalListingComponent
    },
    {
      path      : 'doctors',
      component : DoctorsListingComponent
    }
];

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DashboardComponent,
    HeaderComponent,
    LeftNavComponent,
    ContentPanelComponent,
    AddHospitalComponent,
    HospitalListingComponent,
    CreateHeadComponent,
    DoctorsListingComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot(appRoutes),
    ModalModule.forRoot(),
    BootstrapModalModule,
    LocalStorageModule.withConfig({
      prefix: 'my-app',
      storageType: 'localStorage'
    }),
    AgGridModule.withComponents([]),
    NgGridModule,
    FusionChartsModule,
    NgbModule.forRoot(),
    ReactiveFormsModule
  ],
  providers: [
    {
      provide: Http,
      useFactory: httpFactory,
      deps: [XHRBackend, RequestOptions]
    },
    LoginService,
    HospitalService,
    HttpInterceptorService,
    HeadService
  ],
  bootstrap: [
    AppComponent
  ],
  entryComponents: [
    CreateHeadComponent
  ]
})

export class AppModule {

}
