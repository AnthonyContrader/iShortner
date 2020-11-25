import { Injectable } from '@angular/core';
import { AbstractService } from './abstractservice';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserDTO } from 'src/dto/userdto';
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
        return this.http.post<any>('http://localhost:8080/' + this.type + '/create', url)
    }

    redirect(url: UrlDTO): Observable<UrlDTO>{
        return this.http.post<UrlDTO>('http://localhost:8080/' + this.type + '/redirect', url);
    }
}