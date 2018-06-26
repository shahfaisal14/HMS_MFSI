import { Component, OnInit } from '@angular/core';
import { Hospital } from '../../models/hospital.model';
import { Department } from '../../models/department.model';
import { Room } from '../../models/room.model';
import { MedicalTest } from '../../models/madical-test.model';
import { Laboratory } from '../../models/laboratory.model';
import { Speciality } from '../../models/speciality.model';
import { HospitalService } from '../../services/hospital.service';

@Component({
  selector: 'app-add-hospital',
  templateUrl: './add-hospital.component.html',
  styleUrls: ['./add-hospital.component.less']
})
export class AddHospitalComponent implements OnInit {

  private hospital:Hospital;

  constructor(private hospitalService: HospitalService) {

    // Create speciality of hospital
    let speciality = new Speciality('', '');

    // Create one Room object in roomsList
    let room = new Room('', 2, 0, 2, 0);
    let rooms = new Array<Room>(room);

    // Create one Medical Test Object in medicalTestList
    let medicalTest = new MedicalTest('', '', '', 0);
    let medicalTests = new Array<MedicalTest>(medicalTest);

    // Create one Laboratory object in laboratoriesList
    let laboratory = new Laboratory('', medicalTests);
    let laboratories = new Array<Laboratory>(laboratory);

    // Create one Department object in DepartmentList
    let department = new Department('', '', '');
    let departments = new Array<Department>(department);

    this.hospital = new Hospital('', '', '', '', speciality, departments, laboratories, rooms);
  }

  ngOnInit() {
  }

  addHospital(){
    this.hospitalService.addHospital(this.hospital)
    .subscribe((item) => {
      let dataFromServer = JSON.parse(item._body);
      if(dataFromServer.responseType == 'SUCCESS'){
        alert(dataFromServer.responseData.message);
      }
    });
  }
  
  /* Adds an input field on plus icon click */
  addInputBox (index:string, inputField:string, indexOfParentItemIfAny?:string) {

      let inputFieldItem:any;
      if(inputField=='departments'){
          inputFieldItem = new Department('', '', '');
          this.hospital.departments.push(inputFieldItem);
      }
      if(inputField == 'rooms'){
        inputFieldItem = new Room('', 2, 0, 2, 0);
        this.hospital.rooms.push(inputFieldItem);
      }
      if(inputField == 'laboratories'){
        inputFieldItem = new Laboratory('', new Array<MedicalTest>(new MedicalTest('', '', '', 0)));
        this.hospital.laboratories.push(inputFieldItem);
      }
      if(inputField == 'medicalTests'){
        inputFieldItem = new MedicalTest('', '', '', 0);
        this.hospital.laboratories[indexOfParentItemIfAny].medicalTests.push(inputFieldItem);
      }
  }

  /* Removes an input field on minus icon click */
  removeInputBox($event, inputField, value){
    let index = this.hospital[inputField].indexOf(value);
    if($event.which == 1) {
      this.hospital[inputField].splice(index,1);
    }
  }
}
