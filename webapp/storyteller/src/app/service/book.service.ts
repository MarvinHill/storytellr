import { Injectable } from '@angular/core';
import {AddBookRequest, Book, EditBookRequest} from "../model/book";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class BookService {

  constructor( private http: HttpClient) { }

  getBookById(bookId: string): Observable<Book> {
    return this.http.get<Book>(`${environment.apiUrl}/books/${bookId}`);
  }

  getBooks(): Observable<Book[]> {
    return this.http.get<Book[]>(`${environment.apiUrl}/books/all`);
  }

  addBook(book: AddBookRequest): Observable<Book> {
    return this.http.post<Book>(`${environment.apiUrl}/books/add`, book);
  }

  generateData(): Observable<any> {
    return this.http.get<any>(`${environment.apiUrl}/mock/gen`);
  }

  updateBook(editBook: EditBookRequest): Observable<EditBookRequest> {
    return this.http.put<EditBookRequest>(`${environment.apiUrl}/books/update`, editBook);
  }

  getBooksByAuthor(): Observable<Book[]> {
    return this.http.get<Book[]>(`${environment.apiUrl}/books/user`);
  }
}
