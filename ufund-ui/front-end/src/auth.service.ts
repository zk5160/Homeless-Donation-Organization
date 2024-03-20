import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  users: any[] = [
    {
      id: 1,
      name: 'Bob',
      username: 'Bob',
      password: '123'
    }
  ]

  session : any;
  constructor() { }
  login(username: string, password: string){
    if(this.users.find((u)=>u.username===username && u.password===password))
    {
        this.session = 
    }
  }
}
