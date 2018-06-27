import { Speciality } from "./speciality.model";
import { Department } from "./department.model";
import { Laboratory } from "./laboratory.model";
import { Room } from "./room.model";

export class Hospital{
    name:string;
    address:string;
    contact:string;
    email:string;
    speciality:Speciality;
    departments:Array<Department>;
    laboratories:Array<Laboratory>;
    rooms:Array<Room>;

    constructor(name:string, address:string, contact:string, email:string, speciality:Speciality, 
        departments:Array<Department>, laboratories:Array<Laboratory>, rooms:Array<Room>){
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.email = email;
        this.speciality = speciality;
        this.departments = departments;
        this.laboratories = laboratories;
        this.rooms = rooms;
    }
}