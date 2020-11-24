import { ServerDTO } from './../dto/serverdto';
import { Observable } from 'rxjs';
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
      this.type = 'server';
    }
  
    getUrl(serverDTO: ServerDTO): Observable<ServerDTO> {
      return this.http.post<any>('http://localhost:8080/' + this.type + '/server', serverDTO);
    }
  
  }