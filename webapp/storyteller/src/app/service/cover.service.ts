import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { CoverURI } from '../model/cover';

@Injectable({
  providedIn: 'root'
})
export class CoverService {

  public visible : BehaviorSubject<boolean> = new BehaviorSubject(false);

  constructor( private http: HttpClient) { }

  public previewCover(file : File) : Observable<CoverURI>{
    var fd = new FormData();
    fd.append('file', file);
    return this.http.post<CoverURI>('/api/v1/cover/previewImage', fd);
  }

  public commitImage(file : File, bookId : string) : Observable<CoverURI>{
    var fd = new FormData();
    fd.append('file', file);
    fd.append('bookId', bookId);
    return this.http.post<CoverURI>(`/api/v1/cover/commitImage`, fd);
  }

  public show() {
    if (!this.visible.getValue()) {
      this.visible.next(true);
    }
  }

  public hide() {
    if (this.visible.getValue()) {
      this.visible.next(false);
    }
  }
}
