import { Injectable } from '@angular/core';
import {Chapter, EditChapterRequest} from "../model/chapter";

@Injectable({
  providedIn: 'root'
})
export class ChapterMapperService {

  constructor() { }

  mapChapterToEditChapterRequest(chapter: Chapter): EditChapterRequest {
    return {
      id: chapter.id,
      chapterTitle: chapter.chapterTitle,
      content: chapter.content,
      lastModified: chapter.lastModified,
      bookId: chapter.bookId
    };
  }
}
