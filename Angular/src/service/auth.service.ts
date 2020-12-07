import { Injectable } from '@angular/core';


@Injectable()
export class AuthService {

  constructor() { }

  public isAuthenticated(): boolean {

    let token = sessionStorage.getItem('id_token');

    if (token){

      return true;
    } else {
      return false
    }
  }
}
