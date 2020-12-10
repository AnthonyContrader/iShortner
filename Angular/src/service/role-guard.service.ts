import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { AuthService } from './auth.service';
import { JwtHelperService } from '@auth0/angular-jwt';


@Injectable({
  providedIn: 'root'
})
export class RoleGuardService {

  constructor(public auth: AuthService, public router: Router) {}

  canActivate(route: ActivatedRouteSnapshot): boolean {
    const expectedRole = route.data.expectedRole;
    const helper = new JwtHelperService();
    const token = sessionStorage.getItem('id_token');

    // decodifica il token per prenderne il payload
    const tokenPayload = helper.decodeToken(token);

    if (!this.auth.isAuthenticated()) {
        this.router.navigate(['login']);
        return false;
    }
    //Prendo quello che c'è tra il primo punto ed il secondo punto del token decodificato (Payload)
    //e lo confronto con il ruolo dell'utente (ROLE_ADMIN, ROLE_USER)
    else if(tokenPayload.auth !== expectedRole){
      this.router.navigate(['error-role']);
      return false;
    }
    return true;
  }
}