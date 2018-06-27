export class MedicalTest{

    private name:string;

	private type:string;
	
	private description:string;
	
    private charges:number;
    
    constructor(name:string, type:string, description:string, charges:number){
        this.name = name;
        this.type = type;
        this.description = description;
        this.charges = charges;
    }
}