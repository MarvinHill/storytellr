import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {Book} from "../model/book";

@Injectable({
  providedIn: 'root'
})

/**
 * Service for the library
 */
export class LibraryService {

  constructor(private http: HttpClient) { }

  /**
   * Check if a book is in the library
   * @param bookId the book id of the book to check
   */
  isBookInLibrary(bookId: string): Observable<boolean> {
    return this.http.get<boolean>(`${environment.apiUrl}/library/contains/${bookId}`);
  }

  /**
   * Add a book to the library
   * @param bookId the book id of the book to add
   */
  addBookToLibrary(bookId: string): Observable<Book> {
    return this.http.put<any>(`${environment.apiUrl}/library/add/${bookId}`, {});
  }

  /**
   * Remove a book from the library
   * @param bookId the book id of the book to add
   */
  removeBookFromLibrary(bookId: string): Observable<Book> {
    return this.http.put<any>(`${environment.apiUrl}/library/remove/${bookId}`, {});
  }

  /**
   * Get the library of the logged in user
   */
  getLibrary(): Observable<Book[]> {
    return this.http.get<Book[]>(`${environment.apiUrl}/library/all`);
  }

  /**
   * Get some random books from the library of the logged in user
   */
  getRandomBooks(): Observable<Book[]> {
    return this.http.get<Book[]>(`${environment.apiUrl}/library/random`);
  }

  /**
   * Like a book
   * @param bookId the id of the book to like
   */
  likeBook(bookId: string): Observable<Book> {
    return this.http.put<Book>(`${environment.apiUrl}/library/like/${bookId}`, {});
  }

  /**
   * Unlike a book
   * @param bookId the id of the book to unlike
   */
  unlikeBook(bookId: string): Observable<Book> {
    return this.http.put<Book>(`${environment.apiUrl}/library/unlike/${bookId}`, {});
  }

  /**
   * Check if a book is liked
   * @param bookId the id of the book to check
   */
  isBookLiked(bookId: string): Observable<boolean> {
    return this.http.get<boolean>(`${environment.apiUrl}/library/isLiked/${bookId}`);
  }
}
