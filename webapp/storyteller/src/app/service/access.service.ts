import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { KeycloakService } from 'keycloak-angular';
import { Book } from '../model/book';

@Injectable({
  providedIn: 'root'
})
export class AccessService {

  constructor(private keycloak: KeycloakService, private router: Router) { }

  toEditOrDetailPage(book: Book) {
    if(!this.keycloak.isLoggedIn()) this.router.navigate(['/book-details'], {queryParams: {bookId: book.id}});

    this.keycloak.loadUserProfile().then((p) => {
      if(p == null || p == undefined || book == undefined) return;

      if(p.id == book?.author){
        this.router.navigate(['/edit-details'], {queryParams: {bookId: book.id}})
        return
      }
      this.router.navigate(['/book-details'], {queryParams: {bookId: book.id}})
    });
  }
}
