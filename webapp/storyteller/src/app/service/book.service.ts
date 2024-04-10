import { Injectable } from '@angular/core';
import {Book} from "../model/book";
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

  addBook(book: Book): Observable<Book> {
    return this.http.post<Book>(`${environment.apiUrl}/books/add`, book);
  }

  generateData(): Observable<any> {
    return this.http.get<any>(`${environment.apiUrl}/mock/gen`);
  }
}
