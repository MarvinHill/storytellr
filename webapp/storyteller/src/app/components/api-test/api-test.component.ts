import { HttpClient } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { BehaviorSubject, Observable, Subject } from 'rxjs';
import { Book } from '../../model/book';

@Component({
  selector: 'app-api-test',
  templateUrl: './api-test.component.html',
  styleUrl: './api-test.component.scss',
})

/**
 * ApiTestComponent is a component that allows the admins to test the API endpoints and generate mock data.
 */
export class ApiTestComponent {
  private http: HttpClient = inject(HttpClient);
  public books: BehaviorSubject<Book[]> = new BehaviorSubject<Book[]>([]);
  public file: BehaviorSubject<string | null> = new BehaviorSubject<string | null>(null)

  /**
   * getBooks is a function that sends a request to the server to get all books.
   */
  public getBooks() {
    console.info('run get books');
    this.requestBooks().subscribe((bookResponse) => {
      this.books.next(bookResponse);
    });
  }

  /**
   * postBooks is a function that sends a request to the server to add 100 books.
   */
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

  /**
   * genApi is a function that sends a request to the server to generate mock data.
   */
  genApi(){
    this.http.get('/api/v1/mock/gen').subscribe(response => {
      console.log(JSON.stringify(response));
    });
  }

  /**
   * getPreview is a function that sends a request to the server to get a preview of the image.
   * @param event the event that triggered the function
   */
  public getPreview(event : any) {
    if(event.target.files.item(0) == null) return;
    const formData = new FormData();
    formData.append('file', event.target.files.item(0));
    return this.http.post('/api/v1/cover/previewImage',formData, {observe: 'response'}).subscribe(response => {
      console.log(JSON.stringify(response));
    });
  }

  /**
   * postImage is a function that sends a request to the server to add an image.
   * @param event the event that triggered the function
   */
  private requestBooks(): Observable<Book[]> {
    return this.http.get<Book[]>('/api/v1/books/all');
  }

  /**
   * createGenres is a function that sends a request to the server to create genres.
   * @param event the event that triggered the function
   */
  createGenres() {
    this.http.get('/api/v1/mock/gen/genres').subscribe((response) => {
      console.log('Genres created:', response);
    });
  }
}
