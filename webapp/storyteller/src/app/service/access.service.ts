import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Book } from '../model/book';

@Injectable({
  providedIn: 'root'
})

/**
 * AccessService is used to navigate to different pages
 */
export class AccessService {

  constructor(private router: Router) { }

  /**
   * Navigate to the book details page
   * @param book - the book to be displayed
   */
  toBookDetailsPage(book: Book) {
    this.router.navigate(['/book-details'], {queryParams: {bookId: book.id}});
  }

  /**
   * Navigate to the book edit page
   * @param book - the book to be edited
   */
  toBookEditPage( book: Book) {
    this.router.navigate(['/edit-details'], {queryParams: {bookId: book.id}})
  }
}
