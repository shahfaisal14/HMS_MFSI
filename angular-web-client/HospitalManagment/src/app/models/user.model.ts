import { Role } from "./role.model";

export class User {
    userId: string;
    firstName: string;
    middleName: string;
    lastName: string;

    constructor(userId:string, firstName:string, middleName:string, lastName:string){
        this.userId = userId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }
}