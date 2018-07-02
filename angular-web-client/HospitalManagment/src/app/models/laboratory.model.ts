import { MedicalTest } from "./madical-test.model";

export class Laboratory{
    name:string;
    medicalTests: Array<MedicalTest>;

    constructor(name:string, medicalTests:Array<MedicalTest>){
        this.name = name;
        this.medicalTests = medicalTests;
    }
}