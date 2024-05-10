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
import { BookDisplayComponent } from './components/book-display/book-display.component';
import { BookHeroComponent } from './components/book-hero/book-hero.component';
import { LazyImageComponent } from './components/lazy-image/lazy-image.component';
import { BookShowcaseComponent } from './components/book-showcase/book-showcase.component';
import { SearchResultComponent } from './components/search-result/search-result.component';
import { PaginationComponent } from './components/pagination/pagination.component';
import { EditorComponent } from './components/editor/editor.component';
import { ChapterEditComponent } from './components/chapter-edit/chapter-edit.component';
import { AvatarComponent } from './components/avatar/avatar.component';
import { AvatarMenuItemComponent } from './components/avatar-menu-item/avatar-menu-item.component';
import { PageWrapperComponent } from './components/page-wrapper/page-wrapper.component';
import { WriteOverviewComponent } from './components/write-overview/write-overview.component';
import { ReadPageComponent } from './components/read-page/read-page.component';
import { HeadingComponent } from './components/blocks/heading/heading.component';
import { ParagraphComponent } from './components/blocks/paragraph/paragraph.component';


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
    BookHeroComponent,
    LazyImageComponent,
    BookShowcaseComponent,
    SearchResultComponent,
    PaginationComponent,
    EditorComponent,
    ChapterEditComponent,
    AvatarComponent,
    AvatarMenuItemComponent,
    PageWrapperComponent,
    WriteOverviewComponent,
    ReadPageComponent,
    HeadingComponent,
    ParagraphComponent
  ],
  imports: [
    BrowserModule,
    NgOptimizedImage,
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
    HttpClientModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
