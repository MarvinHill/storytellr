import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {AddChapterRequest, Chapter, EditChapterRequest} from "../model/chapter";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ChapterService {

  constructor(private http: HttpClient) {
  }

  getChapterById(chapterId: string): Observable<Chapter> {
    return this.http.get<Chapter>(`${environment.apiUrl}/chapters/${chapterId}`);
  }

  addChapter(chapter: AddChapterRequest): Observable<Chapter> {
    return this.http.post<Chapter>(`${environment.apiUrl}/chapters/add`, chapter);
  }

  updateChapter(chapter: EditChapterRequest): Observable<Chapter> {
    return this.http.put<Chapter>(`${environment.apiUrl}/chapters/update`, chapter);
  }

  getPublishedChaptersByBookId(bookId: string): Observable<Chapter[]> {
    return this.http.get<Chapter[]>(`${environment.apiUrl}/chapters/${bookId}/published`);
  }

  getNPublishedChaptersByBookId(bookId: string, n: number): Observable<Chapter[]> {
    return this.http.get<Chapter[]>(`${environment.apiUrl}/chapters/${bookId}/published/${n}`);
  }
}
