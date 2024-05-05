import { AfterViewChecked, Component } from '@angular/core';
import { KeycloakService } from 'keycloak-angular';

@Component({
  selector: 'app-top-bar',
  templateUrl: './top-bar.component.html',
  styleUrl: './top-bar.component.scss'
})
export class TopBarComponent implements AfterViewChecked{

  protected username : string | undefined;

  constructor(protected keycloak : KeycloakService){}

  ngAfterViewChecked(): void {
    if(!this.username && this.keycloak.isLoggedIn()){
      this.updateUsername()
    }
  }

  toggleMenu() {
    const burgerMenu = document.getElementById("burgerMenu");
    console.log(burgerMenu);
    if(burgerMenu){
      burgerMenu.classList.toggle("hidden");
      console.log(burgerMenu.classList)
    }
  }

  login(){
    console.log("login user")
    this.keycloak.login({
      redirectUri: window.location.href
    }).then(()=>{
      console.log("get Username after login")
      this.updateUsername()
    });
  }

  async updateUsername(){
    if(this.keycloak.isLoggedIn()){
      this.username = await this.keycloak.loadUserProfile().then((profile)=>{
        return profile.username?.substring(0,1).toUpperCase();
      })
    }
  }
}