import { Injectable } from '@angular/core';
import { AbstractService } from './abstractservice';
import { UserDTO } from 'src/dto/userdto';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { LoginDTO } from 'src/dto/logindto';
import { Observable } from 'rxjs';

/**
 * I service sono decorati da @Injectable. 
 * Qui trovate, oltre ai metodi ereditati dall'Abstract,
 *  il metodo per il login (in mirror con il backend).
 * 
 * @author Vittorio Valent
 * 
 * @see AbstractService
 */
@Injectable({
  providedIn: 'root'
})
export class UserService extends AbstractService<UserDTO>{

  constructor(http: HttpClient) {
    super(http);
    this.type = 'user';
  }

  login(loginDTO: LoginDTO): Observable<UserDTO> {
/*     const object = new HttpHeaders();
    object.set("Authorization", `Bearer ${sessionStorage.getItem('jhi-authenticationtoken')}`);
    const httpoption = {
      headers: object
    } */  
    const c = sessionStorage.getItem("id_token")
    return this.http.post<any>('http://localhost:8080/services/user/api/login', loginDTO, {headers : { Authorization: "Bearer "+ c }})
  }

  authenticate(loginDTO: LoginDTO): Observable<any>{
    return this.http.post<any>('http://localhost:8080/api/authenticate', loginDTO);
  }

}
