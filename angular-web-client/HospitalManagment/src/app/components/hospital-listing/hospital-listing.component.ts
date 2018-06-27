import { Component, OnInit } from '@angular/core';
import { HospitalService } from '../../services/hospital.service';
import { Route } from '@angular/compiler/src/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-hospital-listing',
  templateUrl: './hospital-listing.component.html',
  styleUrls: ['./hospital-listing.component.less']
})
export class HospitalListingComponent implements OnInit {

  constructor(private hospitalService: HospitalService, private router:Router) {

  }

  ngOnInit() {
    this.getHospitals();
  }

  // ag Grid
  columnDefs = [
    {headerName: 'name', field: 'name' },
    {headerName: 'address', field: 'address' },
    {headerName: 'contact', field: 'contact'},
    {headerName: 'speciality', field: 'speciality' },
    {headerName: 'departments', field: 'departments' },
    {headerName: 'laboratories', field: 'laboratories'},
    {headerName: 'rooms', field: 'rooms'},
    {headerName: 'head', field: 'head'}
  ];

  rowData = [
      { name: 'Toyota', address: 'Celica', contact: 35000, speciality: 'No', 
        departments: 'Ortho, ENT', laboratories: 'lab1, lab2', rooms: '2,4', head:'' },
        { name: 'Toyota', address: 'Celica', contact: 35000, speciality: 'No', 
        departments: 'Ortho, ENT', laboratories: 'lab1, lab2', rooms: '2,4', head:'' }
  ];

  getHospitals(){
    this.hospitalService.getHospitals()
    .subscribe((item) => {
      let dataFromServer = JSON.parse(item._body);
      if(dataFromServer.responseType == 'SUCCESS'){
        this.rowData = dataFromServer.responseData;
        console.log(dataFromServer.responseData);
      }
    });
  }

  goTo(route:string){
    if(route == 'addHospital'){
      this.router.navigate(['addHospital']);
    }
  }

}
