
import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { KeycloakAuthGuard, KeycloakService } from 'keycloak-angular';

@Injectable({
  providedIn: 'root'
})

/**
 * AuthGuard class is used to check if the user is authenticated or not.
 */
export class AuthGuard extends KeycloakAuthGuard {
  
  constructor(
    protected override readonly router: Router,
    protected readonly keycloak: KeycloakService
  ) {
    super(router, keycloak);
  }

  /**
   * isAccessAllowed method is used to check if the user is authenticated or not.
   * @param route the route to be checked
   * @param state the state to be checked
   * @returns
   */
  async isAccessAllowed(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Promise<boolean | UrlTree> {

    console.warn("auth guard ran");  
    if (!this.authenticated) {
      await this.keycloak.login({
        redirectUri: window.location.origin + state.url,
      }).finally(()=>{
        console.warn("login request ran")
      });
    }
    console.warn("keycloak is authentcated", this.authenticated)
    return this.authenticated;
  }
}