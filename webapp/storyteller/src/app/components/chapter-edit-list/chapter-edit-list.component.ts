import {Component, Input, OnInit} from '@angular/core';
import {Book} from "../../model/book";
import {AddChapterRequest, Chapter} from "../../model/chapter";
import {ChapterService} from "../../service/chapter.service";
import {Router} from "@angular/router";
import {firstValueFrom} from "rxjs";

@Component({
  selector: 'app-chapter-edit-list',
  templateUrl: './chapter-edit-list.component.html',
  styleUrl: './chapter-edit-list.component.scss'
})

/**
 * Component to display the list of chapters of a book
 */
export class ChapterEditListComponent implements OnInit {
  @Input() book!: Book;
  chapters: Chapter[] = [];

  constructor(private chapterService: ChapterService, private router: Router) {
  }

  ngOnInit() {
    this.getChapters();
  }

  /**
   * Gets all chapters of the book
   */
  async getChapters() {
    for (let chapterId of this.book.chapterIds) {
      const chapter = await firstValueFrom(this.chapterService.getChapterById(chapterId));
      this.chapters.push(chapter);
    }
  }


  /**
   * Adds a new chapter to the book
   */
  addChapter() {
    const chapter: AddChapterRequest = {
      chapterTitle: 'New Chapter',
      content: '',
      lastModified: new Date(),
      bookId: this.book.id,
      published: false
    };

    this.chapterService.addChapter(chapter).subscribe({
      next: (resp: Chapter) => {
        this.router.navigate(['/editor'], {queryParams: {chapterId: resp.id}});

      },
      error: (error: any) => {
        console.error(error.message);
      }
    });

  }

  /**
   * Navigates to the editor to update the content of the chapter
   * @param chapter the chapter to update
   */
  updateChapterContent(chapter: Chapter) {
    this.router.navigate(['/editor'], {queryParams: {chapterId: chapter.id}});
  }
}
