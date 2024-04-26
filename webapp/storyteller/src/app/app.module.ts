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
import { EditDetailsComponent } from './components/edit-details/edit-details.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { BookSettingsComponent } from './components/book-settings/book-settings.component';
import { ChapterEditListComponent } from './components/chapter-edit-list/chapter-edit-list.component';
import { LeadingZeroPipe } from './pipes/leading-zero.pipe';
import { BookDisplayComponent } from './book-display/book-display.component';
import { BookHeroComponent } from './book-hero/book-hero.component';


function initializeKeycloak(keycloak: KeycloakService) {
  return () =>
    keycloak.init({
      config: {
        url: 'http://localhost:8080/auth',
        realm: 'storytellr',
        clientId: 'storytellr-frontend',
      },
      initOptions: {
        onLoad:'check-sso',
      },
      
    });
}



@NgModule({
  declarations: [
    AppComponent,
    TopBarComponent,
    CoverUploadComponent,
    DiscoveryPageComponent,
    ApiTestComponent,
    BookDetailComponent,
    EditDetailsComponent,
    BookSettingsComponent,
    ChapterEditListComponent,
    LeadingZeroPipe,
    BookDisplayComponent,
    BookHeroComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    KeycloakAngularModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
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
