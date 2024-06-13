import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { KeycloakService } from 'keycloak-angular';
import { Book } from '../model/book';

@Injectable({
  providedIn: 'root'
})
export class AccessService {

  constructor(private router: Router) { }

  toBookDetailsPage(book: Book) {
    this.router.navigate(['/book-details'], {queryParams: {bookId: book.id}});
  }

  toBookEditPage( book: Book) {
    this.router.navigate(['/edit-details'], {queryParams: {bookId: book.id}})
  }
}
