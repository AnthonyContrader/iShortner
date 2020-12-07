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
  // decode the token to get its payload
  const tokenPayload = helper.decodeToken(token)
  if (!this.auth.isAuthenticated() || tokenPayload.auth !== expectedRole) {
       
      this.router.navigate(['login']); //pagina di errore
      return false;
  }
  return true;
}
}
