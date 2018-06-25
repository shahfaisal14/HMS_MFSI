export class LoginModel{
    userCode:string;
    passCode:string;

    constructor(userCode: string, passCode: string){
        this.userCode = userCode;
        this.passCode = passCode;
    }   
}