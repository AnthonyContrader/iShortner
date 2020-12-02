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
      return this.http.post<any>('http://localhost:8080/services/url/api/urls', url, {
        headers: {Authorization: "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX0FETUlOLFJPTEVfVVNFUiIsImV4cCI6MTYwNjkyMDYwOH0.su5P1mWP_ZqS0Fp4e2Y8xH99ZVe4KRZrwch_s4jwJ1wKwc8xvetCO8qAWLkxr28DcqVLcsOGR2hVMFFb_eIAEg"}
      });
    }

    redirect(url: UrlDTO): Observable<UrlDTO>{
        return this.http.post<UrlDTO>('http://localhost:8080/' + this.type + '/redirect', url);
    }

    getUrlFromUser(id: number): Observable<UrlDTO[]>{
      return this.http.get<UrlDTO[]>('http://localhost:8080/' + this.type + '/readinfourl?id=' +id);
  }
}