import { APP_INITIALIZER, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { KeycloakAngularModule, KeycloakService } from 'keycloak-angular';
import { TopBarComponent } from './components/top-bar/top-bar.component';
import { DiscoveryPageComponent } from './components/discovery-page/discovery-page.component';
import {NgOptimizedImage} from "@angular/common";

function initializeKeycloak(keycloak: KeycloakService) {
  return () =>
    keycloak.init({
      config: {
        url: 'http://localhost:8080/auth',
        realm: 'storytellr',
        clientId: 'storytellr-frontend'
      },
      initOptions: {
        onLoad: 'check-sso',
        silentCheckSsoRedirectUri:
          "http://localhost:8080/"
      }
    });
}

@NgModule({
  declarations: [
    AppComponent,
    TopBarComponent,
    DiscoveryPageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule, 
    KeycloakAngularModule
  ],
  providers: [
    {
      provide: APP_INITIALIZER,
      useFactory: initializeKeycloak,
      multi: true,
      deps: [KeycloakService]
    },
    AppRoutingModule,
    NgOptimizedImage
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
