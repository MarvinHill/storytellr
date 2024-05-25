import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {Book} from "../model/book";

@Injectable({
  providedIn: 'root'
})
export class LibraryService {

  constructor(private http: HttpClient) { }

  isBookInLibrary(bookId: string): Observable<boolean> {
    return this.http.get<boolean>(`${environment.apiUrl}/library/contains/${bookId}`);
  }

  addBookToLibrary(bookId: string): Observable<Book> {
    return this.http.put<any>(`${environment.apiUrl}/library/add/${bookId}`, {});
  }

  removeBookFromLibrary(bookId: string): Observable<Book> {
    return this.http.put<any>(`${environment.apiUrl}/library/remove/${bookId}`, {});
  }

  getLibrary(): Observable<Book[]> {
    return this.http.get<Book[]>(`${environment.apiUrl}/library/all`);
  }

  getRandomBooks(): Observable<Book[]> {
    return this.http.get<Book[]>(`${environment.apiUrl}/library/random`);
  }
}
