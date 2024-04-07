import { HttpClient } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { BehaviorSubject, Observable, Subject } from 'rxjs';
import { Book } from './Book';

@Component({
  selector: 'app-api-test',
  templateUrl: './api-test.component.html',
  styleUrl: './api-test.component.scss',
})
export class ApiTestComponent {
  private http: HttpClient = inject(HttpClient);
  public books: BehaviorSubject<Book[]> = new BehaviorSubject<Book[]>([]);
  public file: BehaviorSubject<string | null> = new BehaviorSubject<string | null>(null)

  public getBooks() {
    console.info('run get books');
    this.requestBooks().subscribe((bookResponse) => {
      this.books.next(bookResponse);
    });
  }

  public postBooks() {
    console.info('start posting books');
    for (let index = 0; index <= 100; index++) {
      console.info('post book');
      this.http
        .post('/api/v1/books/add', {
          title: `title ${index}`,
          genreId: '1b8b0883-596d-42b0-be60-b8793f22a572',
          author: `author ${index}`,
          description: `description ${index}`,
        })
        .subscribe((response) => {
          console.log('Book added successfully:', response);
        });
    }
  }

  public getPreview(event : any) {
    if(event.target.files.item(0) == null) return;
    const formData = new FormData();
    formData.append('file', event.target.files.item(0));
    return this.http.post('/api/v1/cover/previewImage',formData, {observe: 'response'}).subscribe(response => {
      console.log(JSON.stringify(response));
    });
  }

  private requestBooks(): Observable<Book[]> {
    return this.http.get<Book[]>('/api/v1/books/all');
  }
}
