import { Injectable } from '@angular/core';
import {Chapter, EditChapterRequest} from "../model/chapter";

@Injectable({
  providedIn: 'root'
})

/**
 * Service for mapping chapters
 */
export class ChapterMapperService {

  constructor() { }

  /**
   * Maps a chapter to an EditChapterRequest
   * @param chapter the chapter to map
   */
  mapChapterToEditChapterRequest(chapter: Chapter): EditChapterRequest {
    return {
      id: chapter.id,
      chapterTitle: chapter.chapterTitle,
      content: chapter.content,
      lastModified: chapter.lastModified,
      bookId: chapter.bookId,
      published: chapter.published
    };
  }
}
