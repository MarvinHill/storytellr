import { APP_INITIALIZER, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { KeycloakAngularModule, KeycloakService } from 'keycloak-angular';
import { TopBarComponent } from './components/top-bar/top-bar.component';
import { DiscoveryPageComponent } from './components/discovery-page/discovery-page.component';
import {NgOptimizedImage} from "@angular/common";
import { ApiTestComponent } from './components/api-test/api-test.component';
import { HttpClientModule } from '@angular/common/http';
import { BookDetailComponent } from './components/book-detail/book-detail.component';
import { CoverUploadComponent } from './components/cover-upload/cover-upload.component';

function initializeKeycloak(keycloak: KeycloakService) {
  return () =>
    keycloak.init({
      config: {
        url: 'http://localhost:8080/auth',
        realm: 'storytellr',
        clientId: 'storytellr-frontend'
      },
      initOptions: {
        onLoad:'login-required',
      }
    });
}



@NgModule({
  declarations: [
    AppComponent,
    TopBarComponent,
    CoverUploadComponent,
    DiscoveryPageComponent,
    ApiTestComponent,
    BookDetailComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule, 
    KeycloakAngularModule,
    HttpClientModule
  ],
  providers: [
    {
      provide: APP_INITIALIZER,
      useFactory: initializeKeycloak,
      multi: true,
      deps: [KeycloakService]
    },
    AppRoutingModule,
    NgOptimizedImage,
    HttpClientModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
