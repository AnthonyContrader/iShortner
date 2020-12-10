import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HttpInterceptorService implements HttpInterceptor{

  constructor() { }

  /**
   * Metodo per intercettare tutte le chiamate HTTP
   * Bisogna aggiungere al Provider di app.module il Providing:
   * { 
      provide: HTTP_INTERCEPTORS, 
      useClass: name_Interceptor, 
      multi: true 
      }
   * @param httpRequest 
   * @param next 
   */
  intercept(httpRequest: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let c = sessionStorage.getItem("id_token");
    if(c != null){
      return next.handle(httpRequest.clone({ setHeaders: { Authorization: "Bearer " + c }}));
    }
    return next.handle(httpRequest);
  }
}
