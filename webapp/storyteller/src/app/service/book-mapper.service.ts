import { Injectable } from '@angular/core';
import {Book, EditBookRequest} from "../model/book";

@Injectable({
  providedIn: 'root'
})

/**
 * BookMapperService is used to map book data
 */
export class BookMapperService {

  constructor() { }

  /**
   * Maps a book to an EditBookRequest
   * @param book the book to be mapped
   */
  toEditBookRequest(book: Book): EditBookRequest {
    return {
      id: book.id,
      title: book.title,
      genreId: book.genreId,
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
