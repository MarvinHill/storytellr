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
    return this.http.get<Book>(`${environment.apiUrl}/book/${bookId}`);
  }
}
