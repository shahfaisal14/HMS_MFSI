import { MedicalTest } from "./madical-test.model";

export class Laboratory{
    private name:string;
    private medicalTests: Array<MedicalTest>;

    constructor(name:string, medicalTests:Array<MedicalTest>){
        this.name = name;
        this.medicalTests = medicalTests;
    }
}