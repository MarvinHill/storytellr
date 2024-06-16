import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {AddCommentRequest, Comment} from "../model/comment";

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(private http: HttpClient) { }

  getCommentsByChapterId(chapterId: string) {
    return this.http.get<Comment[]>(`${environment.apiUrl}/comments/chapter/${chapterId}`);
  }

  addComment(comment: AddCommentRequest) {
    return this.http.post<Comment>(`${environment.apiUrl}/comments/add`, comment);
  }

  getCommentsByBlockId(chapterId: string, blockId: string) {
    return this.http.get<Comment[]>(`${environment.apiUrl}/comments/block/${chapterId}/${blockId}`);
  }
}
