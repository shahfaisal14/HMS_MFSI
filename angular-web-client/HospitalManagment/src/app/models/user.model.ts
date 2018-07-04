import { Role } from "./role.model";

export class User {
    userId: string;
    firstName: string;
    middleName: string;
    lastName: string;
    email:string;
    dateOfBirth: Date;

    constructor(userId:string, firstName:string, middleName:string, lastName:string, email:string, dateOfBirth:Date){
        this.userId = userId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }
}