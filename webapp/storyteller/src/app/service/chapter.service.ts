import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {AddChapterRequest, Chapter, EditChapterRequest} from "../model/chapter";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})

/**
 * Service for Chapters
 */
export class ChapterService {

  constructor(private http: HttpClient) {
  }

  /**
   * Get chapter by id
   * @param chapterId the id of the chapter
   */
  getChapterById(chapterId: string): Observable<Chapter> {
    return this.http.get<Chapter>(`${environment.apiUrl}/chapters/${chapterId}`);
  }

  /**
   * Adds a new chapter
   * @param chapter the chapter to add
   */
  addChapter(chapter: AddChapterRequest): Observable<Chapter> {
    return this.http.post<Chapter>(`${environment.apiUrl}/chapters/add`, chapter);
  }

  /**
   * Updates a chapter
   * @param chapter the updated chapter
   */
  updateChapter(chapter: EditChapterRequest): Observable<Chapter> {
    return this.http.put<Chapter>(`${environment.apiUrl}/chapters/update`, chapter);
  }

  /**
   * Get all published chapters by their book id
   * @param bookId
   */
  getPublishedChaptersByBookId(bookId: string): Observable<Chapter[]> {
    return this.http.get<Chapter[]>(`${environment.apiUrl}/chapters/${bookId}/published`);
  }

  /**
   * Get the first n published chapters by their book id
   * @param bookId the id of the book to get the chapters from
   * @param n the number of chapters to get
   */
  getNPublishedChaptersByBookId(bookId: string, n: number): Observable<Chapter[]> {
    return this.http.get<Chapter[]>(`${environment.apiUrl}/chapters/${bookId}/published/${n}`);
  }
}
