import { Injectable } from '@angular/core';
import {Book, EditBookRequest} from "../model/book";

@Injectable({
  providedIn: 'root'
})
export class BookMapperService {

  constructor() { }

  toEditBookRequest(book: Book): EditBookRequest {
    return {
      id: book.id,
      title: book.title,
      genreId: book.genreId,
      author: book.author,
      description: book.description,
      catchphrase: book.catchphrase,
      chapterIds: book.chapterIds,
      tags: book.tags,
      public: book.public,
      adultContent: book.adultContent,
      commentsDeactivated: book.commentsDeactivated,
      finished: book.finished
    };
  }
}
