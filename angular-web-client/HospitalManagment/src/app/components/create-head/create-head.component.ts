import { Component, OnInit, Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Head } from '../../models/head.model';
import * as _ from 'lodash'; 
import { HospitalService } from '../../services/hospital.service';
import { Hospital } from '../../models/hospital.model';

@Component({
  selector: 'app-create-head',
  templateUrl: './create-head.component.html',
  styleUrls: ['./create-head.component.less']
})

export class CreateHeadComponent implements OnInit {

  @Input() id: number;
  // /createHeadForm: FormGroup = new FormGroup();
  head: Head = new Head('', '', '', '', null);
  hospitalsList: Array<Hospital> = new Array<Hospital>();
  selectedHospital: Hospital;

  constructor(
    public ngbActiveModal: NgbActiveModal, 
    public formBuilder: FormBuilder,
    private hospitalService: HospitalService) {

  }

  ngOnInit() {
    this.getHospitals();
  }

  private submitForm() {
    console.log("");
    console.log(this.head);
  }

  closeModal(){
    this.ngbActiveModal.close('Modal Closed');
  }

  getHospitals(){
    this.hospitalService.getHospitals()
    .subscribe((item) => {
      let dataFromServer = JSON.parse(item._body);
      if(dataFromServer.responseType == 'SUCCESS'){
        this.hospitalsList = dataFromServer.responseData;
       _.each(this.hospitalsList, (key, val)=>{
         console.log(key);
         console.log(val);  
       });
      }
    });
  }
}
