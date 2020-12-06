import { ServerDTO } from './../dto/serverdto';
import { Observable} from 'rxjs';
import { map } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import { UrlDTO } from 'src/dto/urldto';
import { AbstractService } from './abstractservice';
import { Injectable } from '@angular/core';

/**
 * I service sono decorati da @Injectable. 
 * Qui trovate, oltre ai metodi ereditati dall'Abstract,
 *  il metodo per il login (in mirror con il backend).
 * 
 * @see AbstractService
 */
@Injectable({
    providedIn: 'root'
  })
  export class ServerService extends AbstractService<ServerDTO>{
  
    constructor(http: HttpClient) {
      super(http);
      this.type = 'user';
    }
  
    getUrl(serverDTO: ServerDTO): Observable<ServerDTO> {
      return this.http.post<any>('http://localhost:8080/' + this.type + '/server', serverDTO);
    }

    getInfoUrl(id: number): Observable<ServerDTO[]>{
      return this.http.get<ServerDTO[]>('http://localhost:' + this.port + '/services/server/api/server/getinfo/' +id)
      .pipe(map((res) => {
        return res;
      }));
    }

    createInfo(id : number): Observable<ServerDTO>{
      return this.http.post<ServerDTO>('http://localhost:8080/services/server/api/servers?id=' +id, id);
    }
  
  }