import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class BasicAuthHttpInterceptorService {

  temp:string;
  constructor() { }
  intercept(req: HttpRequest<any>, next: HttpHandler) {

    this.temp=req.url;
    this.temp=this.temp.substr(0,26);

    if(this.temp==="https://www.googleapis.com"){
      sessionStorage.removeItem('tocken');
    }else{

      if (sessionStorage.getItem('username') && sessionStorage.getItem('token')) {
        req = req.clone({
          setHeaders: {
            Authorization: sessionStorage.getItem('token')   
          }
        })
      }
    }
    return next.handle(req);
  }
}
