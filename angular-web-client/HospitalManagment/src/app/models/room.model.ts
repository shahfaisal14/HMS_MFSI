export class Room{
    roomNumber:string;
    totalBeds:number;
    occupiedBeds:number;
    availableBeds:number;
    chargesPerDay:number;

    constructor(roomNumber:string, totalBeds:number, occupiedBeds:number, availableBeds:number, chargesPerDay:number){
        this.availableBeds = availableBeds;
        this.chargesPerDay = chargesPerDay;
        this.occupiedBeds = occupiedBeds;
        this.roomNumber = roomNumber;
        this.totalBeds = totalBeds;
    }
}