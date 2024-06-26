import { AfterViewChecked, Component } from '@angular/core';
import { Router } from '@angular/router';
import { KeycloakService } from 'keycloak-angular';

@Component({
  selector: 'app-top-bar',
  templateUrl: './top-bar.component.html',
  styleUrl: './top-bar.component.scss'
})

/**
 * Component to display the top bar of the application
 */
export class TopBarComponent implements AfterViewChecked{

  protected username : string | undefined;

  constructor(protected keycloak : KeycloakService, protected router: Router){}

  ngAfterViewChecked(): void {
    if(!this.username && this.keycloak.isLoggedIn()){
      this.updateUsername()
    }
  }

  /**
   * Toggle the burger menu
   */
  toggleMenu() {
    const burgerMenu = document.getElementById("burgerMenu");
    console.log(burgerMenu);
    if(burgerMenu){
      burgerMenu.classList.toggle("hidden");
      console.log(burgerMenu.classList)
    }
  }

  /**
   * Logout the user
   */
  logout(){
    this.router.navigate(['/discovery']);
    setTimeout(()=>{this.keycloak.logout()})
  }

  /**
   * Login the user
   */
  login(){
    console.log("login user")
    this.keycloak.login({
      redirectUri: window.location.href
    }).then(()=>{
      console.log("get Username after login")
      this.updateUsername()
    });
  }

  /**
   * Update the username in the top bar
   */
  async updateUsername(){
    if(this.keycloak.isLoggedIn()){
      this.username = await this.keycloak.loadUserProfile().then((profile)=>{
        return profile.username?.substring(0,1).toUpperCase();
      })
    }
  }
}