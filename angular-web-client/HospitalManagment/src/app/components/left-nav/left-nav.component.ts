import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-left-nav',
  templateUrl: './left-nav.component.html',
  styleUrls: ['./left-nav.component.less']
})
export class LeftNavComponent implements OnInit {

  private selectedItem: string;
  private userRole: string;
  constructor(private router: Router) { }

  ngOnInit() {

    this.userRole = localStorage.getItem('userRole');

    let currentUrl = this.router.url;
    if(currentUrl == '/admin/dashboard'){
      this.selectedItem = "Dashboard";
    }
    if(currentUrl == '/hospitals'){
      this.selectedItem = "Hospitals List";
    }
    if(currentUrl == '/doctors'){
      this.selectedItem = "Doctors List";
    }
    if(currentUrl = '/hospital/new'){
      this.selectedItem = "New Hospital";
    }
  }

  goTo(item:string){
    if(item == 'adminDashboard'){
      this.router.navigate(['admin/dashboard']);
    }
    if(item == 'hospitals'){
      this.router.navigate(['hospitals']);
    }
    if(item == 'doctors'){
      this.router.navigate(['doctors']);
    }
  }

}
