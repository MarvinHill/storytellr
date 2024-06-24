import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {Genre} from "../model/genre";
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})

/**
 * Service for Genres
 */
export class GenreService {

  constructor(private http: HttpClient) { }

  /**
   * Get all genres
   */
  getAllGenres(): Observable<Genre[]> {
    return this.http.get<Genre[]>(`${environment.apiUrl}/genres/all`);
  }

  /**
   * Get a genre by its id
   * @param genreId the id of the genre
   */
  getGenreById(genreId: string): Observable<Genre> {
    return this.http.get<Genre>(`${environment.apiUrl}/genres/${genreId}`);
  }
}
