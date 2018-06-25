export class Room{
    private roomNumber:string;
    private totalBeds:number;
    private occupiedBeds:number;
    private availableBeds:number;
    private chargesPerDay:number;

    constructor(roomNumber:string, totalBeds:number, occupiedBeds:number, availableBeds:number, chargesPerDay:number){
        this.availableBeds = availableBeds;
        this.chargesPerDay = chargesPerDay;
        this.occupiedBeds = occupiedBeds;
        this.roomNumber = roomNumber;
        this.totalBeds = totalBeds;
    }
}