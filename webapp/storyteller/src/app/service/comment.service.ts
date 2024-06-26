import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {AddCommentRequest, Comment} from "../model/comment";

@Injectable({
  providedIn: 'root'
})

/**
 * Service for Comments
 */
export class CommentService {

  constructor(private http: HttpClient) { }

  /**
   * Get all comments by their chapter id
   * @param chapterId the id of the chapter to get the comments from
   */
  getCommentsByChapterId(chapterId: string) {
    return this.http.get<Comment[]>(`${environment.apiUrl}/comments/${chapterId}`);
  }

  /**
   * Add a new comment
   * @param comment the comment to add
   */
  addComment(comment: AddCommentRequest) {
    return this.http.post<Comment>(`${environment.apiUrl}/comments/add`, comment);
  }

  /**
   * Get all comments by their block id and chapter id
   * @param chapterId the id of the chapter to get the comments from
   * @param blockId the id of the block to get the comments from
   */
  getCommentsByBlockId(chapterId: string, blockId: string) {
    return this.http.get<Comment[]>(`${environment.apiUrl}/comments/${chapterId}/${blockId}`);
  }
}
