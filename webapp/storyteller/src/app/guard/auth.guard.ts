
import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { KeycloakAuthGuard, KeycloakService } from 'keycloak-angular';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard extends KeycloakAuthGuard {
  
  constructor(
    protected override readonly router: Router,
    protected readonly keycloak: KeycloakService
  ) {
    super(router, keycloak);
  }
  
  async isAccessAllowed(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Promise<boolean | UrlTree> {

    console.warn("auth guard ran");  
    if (!this.authenticated) {
      console.warn("keycloak is authenticated")
      /*
      await this.keycloak.login({
        redirectUri: window.location.origin + state.url,
      }).finally(()=>{
        console.warn("login request ran")
      });
      */
    }
    return this.authenticated;
  }
}