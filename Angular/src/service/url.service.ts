import { Injectable } from '@angular/core';
import { AbstractService } from './abstractservice';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UrlDTO } from 'src/dto/urldto';

@Injectable({
    providedIn: 'root'
  })
  export class UrlService extends AbstractService<UrlDTO>{
  
    

    constructor(protected http: HttpClient) {
      super(http);
      this.type = 'user';
    }

    create(url: UrlDTO): Observable<UrlDTO> {
      return this.http.post<any>('http://localhost:8080/services/url/api/urls', url);
    }

    redirect(url: UrlDTO): Observable<UrlDTO>{
        return this.http.post<UrlDTO>('http://localhost:8080/services/url/api/redirect', url);
    }

    //Da sistemare (url)
    getUrlFromUser(id: number): Observable<UrlDTO[]>{
      return this.http.get<UrlDTO[]>('http://localhost:8080/services/url/api/urls/user/' +id);
  }
}