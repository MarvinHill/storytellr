import { Injectable } from '@angular/core';
import {AddBookRequest, Book, EditBookRequest} from "../model/book";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})

/**
 * BookService is used to interact with the backend to get book data
 */
export class BookService {

  constructor( private http: HttpClient) { }

  /**
   * Get a book by its id
   * @param bookId the id of the book
   */
  getBookById(bookId: string): Observable<Book> {
    return this.http.get<Book>(`${environment.apiUrl}/books/${bookId}`);
  }

  /**
   * Get all books from the backend
   */
  getBooks(): Observable<Book[]> {
    return this.http.get<Book[]>(`${environment.apiUrl}/books/all`);
  }

  /**
   * Add a new book to the backend
   * @param book the book to be added
   */
  addBook(book: AddBookRequest): Observable<Book> {
    return this.http.post<Book>(`${environment.apiUrl}/books/add`, book);
  }

  /**
   * Delete a book from the backend
   */
  generateData(): Observable<any> {
    return this.http.get<any>(`${environment.apiUrl}/mock/gen`);
  }

  /**
   * Update a book in the backend
   * @param editBook the book to be updated
   */
  updateBook(editBook: EditBookRequest): Observable<EditBookRequest> {
    return this.http.put<EditBookRequest>(`${environment.apiUrl}/books/update`, editBook);
  }

  /**
   * Gets all books by the logged in author
   */
  getBooksByAuthor(): Observable<Book[]> {
    return this.http.get<Book[]>(`${environment.apiUrl}/books/user`);
  }

  /**
   * Get all books with their published chapters
   * @param bookId the id of the book
   */
  getBookWithPublishedChapters(bookId: string): Observable<Book> {
    return this.http.get<Book>(`${environment.apiUrl}/books/${bookId}/published`);
  }

  /**
   * Get the progress of a book
   * @param bookId the id of the book
   */
  getBookProgress(bookId: string): Observable<number> {
    return this.http.get<number>(`${environment.apiUrl}/books/progress/${bookId}`);
  }

  /**
   * Increase the progress of a book
   * @param bookId the id of the book
   * @param progress the current progress of the book
   */
  increaseBookProgress(bookId: string, progress: number): Observable<number> {
    console.log("Increasing progress by" + progress);
    return this.http.put<number>(`${environment.apiUrl}/books/progress/increase/${bookId}/${progress}`, {});
  }
}
