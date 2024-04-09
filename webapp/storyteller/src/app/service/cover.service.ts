import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CoverService {

  constructor( private http: HttpClient) { }

  public previewCover(file : File) : Observable<Object>{
    return this.http.post('/api/cover/preview', file);
  }

}
