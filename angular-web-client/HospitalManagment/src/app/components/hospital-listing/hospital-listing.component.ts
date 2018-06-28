import { Component, OnInit} from '@angular/core';
import { HospitalService } from '../../services/hospital.service';
import { Router } from '@angular/router';
import { Hospital } from '../../models/hospital.model';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

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
    {headerName: 'Hospital Name', field: 'name' },
    {headerName: 'Address', field: 'address' },
    {headerName: 'Contact', field: 'contact'},
    {headerName: 'Speciality', field: 'speciality' },
    {headerName: 'Departments', field: 'departments' },
    {headerName: 'Laboratories', field: 'laboratories'},
    {headerName: 'Rooms', field: 'rooms'},
    {headerName: 'Head', field: 'head'}
  ];

  private rowData: Array<any>;

  getHospitals(){
    this.hospitalService.getHospitals()
    .subscribe((item) => {
      let dataFromServer = JSON.parse(item._body);
      if(dataFromServer.responseType == 'SUCCESS'){
        this.rowData = this.prepareHospitalGridData(dataFromServer.responseData);
        console.log(dataFromServer.responseData);
      }
    });
  }

  goTo(route:string){
    if(route == 'addHospital'){
      this.router.navigate(['addHospital']);
    }
  }
  
  assignHead(){

  }

  private prepareHospitalGridData(hospitals:Array<Hospital>){

    let gridData: Array<any> = new Array();
    for(let hospital of hospitals){
      let rowData:any;
      rowData = hospital;

      let departmentNamesList: Array<string> = new Array<string>();
      for(let department of hospital.departments){
        departmentNamesList.push(department.name);
      }
      rowData.departments = departmentNamesList.join(", ");
      
      let laboratoryNamesList: Array<string> = new Array<string>();
      for(let laboratory of hospital.laboratories){
        laboratoryNamesList.push(laboratory.name);
      }
      rowData.laboratories = laboratoryNamesList.join(", ");

      let roomsNamesList: Array<string> = new Array<string>();
      for(let room of hospital.rooms){
        roomsNamesList.push(room.roomNumber);
      }
      rowData.rooms = roomsNamesList.join(", ");

      rowData.speciality = hospital.speciality.name;

      gridData.push(rowData);
    }
    return gridData;
  }
}
