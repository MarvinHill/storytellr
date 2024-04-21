import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Chapter} from "../model/chapter";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ChapterService {

  constructor(private http: HttpClient) { }

  getChapterById(chapterId: string): Observable<Chapter> {
    return this.http.get<Chapter>(`${environment.apiUrl}/chapters/${chapterId}`);
  }
}
