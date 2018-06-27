import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-left-nav',
  templateUrl: './left-nav.component.html',
  styleUrls: ['./left-nav.component.less']
})
export class LeftNavComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
  }

  goTo(item:string){
    if(item == 'adminDashboard'){
      this.router.navigate(['dashboard']);
    }
    if(item == 'hospitals'){
      this.router.navigate(['hospitalList']);
    }
  }

}
