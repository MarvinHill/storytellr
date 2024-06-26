import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { CoverURI } from '../model/cover';

@Injectable({
  providedIn: 'root'
})

/**
 * Service for Cover
 */
export class CoverService {

  public visible : BehaviorSubject<boolean> = new BehaviorSubject(false);

  constructor( private http: HttpClient) { }

  /**
   * Get the cover preview
   * @param file the file to get the preview for
   */
  public previewCover(file : File) : Observable<CoverURI>{
    var fd = new FormData();
    fd.append('file', file);
    return this.http.post<CoverURI>('/api/v1/cover/previewImage', fd);
  }

  /**
   * Get the default cover uri
   */
  getDefaultUri(): CoverURI {
    let coverUri : CoverURI = {smImageUri: "assets/images/book-cover.png", originalImageUri: "assets/images/book-cover.png", lgImageUri: "assets/images/book-cover.png"} as CoverURI
    return coverUri; 
  }

  /**
   * Commit the image to the server
   * @param file the file to commit
   * @param bookId the book id to commit the image to
   */
  public commitImage(file : File, bookId : string) : Observable<CoverURI>{
    var fd = new FormData();
    fd.append('file', file);
    fd.append('bookId', bookId);
    return this.http.post<CoverURI>(`/api/v1/cover/commitImage`, fd);
  }

  /**
   * Show the cover
   */
  public show() {
    if (!this.visible.getValue()) {
      this.visible.next(true);
    }
  }

  /**
   * Hide the cover
   */
  public hide() {
    if (this.visible.getValue()) {
      this.visible.next(false);
    }
  }
}
