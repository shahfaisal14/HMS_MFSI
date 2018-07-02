import { User } from "./user.model";
import { Hospital } from "./hospital.model";
import { last } from "@angular/router/src/utils/collection";

export class Head extends User {
    hospital: Hospital;

    constructor(userId:string, firstName:string, middleName:string, lastName:string, hospital:Hospital){
        super(userId, firstName, middleName, lastName);
        this.hospital = hospital;
    }
}