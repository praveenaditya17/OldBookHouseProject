import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  object:any;
  constructor(private httpClient:HttpClient) { }

    // this method is use to loging the user  
    authenticate(username, password) {
     return  this.httpClient.post<any>('http://localhost:8080/authenticate',{username,password});
    }
  
    isUserLoggedIn() {
      let user = sessionStorage.getItem('username');
      return !(user === null);
    }
    logOut() {
      sessionStorage.removeItem('username');
    }
}
