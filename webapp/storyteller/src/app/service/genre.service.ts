import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {Genre} from "../model/genre";
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class GenreService {

  constructor(private http: HttpClient) { }

  getAllGenres(): Observable<Genre[]> {
    return this.http.get<Genre[]>(`${environment.apiUrl}/genres/all`);
  }

  getGenreById(genreId: string): Observable<Genre> {
    return this.http.get<Genre>(`${environment.apiUrl}/genres/${genreId}`);
  }

  getGenres(){
    return this.http.get<Genre[]>(`${environment.apiUrl}/genres/all`);
  }
}
