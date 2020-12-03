import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HttpInterceptorService implements HttpInterceptor{

  constructor() { }

  // te li commenti tu, grazie 
  intercept(httpRequest: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let c = sessionStorage.getItem("id_token");
    if(c != null){
      return next.handle(httpRequest.clone({ setHeaders: { Authorization: "Bearer " + c }}));
    }
    return next.handle(httpRequest);
  }
}
