import {AfterViewChecked, AfterViewInit, Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {BookService} from "../../service/book.service";
import {Book} from "../../model/book";
import {ChapterService} from "../../service/chapter.service";
import {Chapter} from "../../model/chapter";
import {firstValueFrom, switchMap} from "rxjs";

@Component({
  selector: 'app-read-page',
  templateUrl: './read-page.component.html',
  styleUrl: './read-page.component.scss'
})
export class ReadPageComponent implements OnInit, AfterViewInit, AfterViewChecked {
  bookId!: string;
  book!: Book;
  chapters: Chapter[] = [];
  counter: number = 0;
  currentChapter: number = 0;

  @ViewChild('chapterContainer') chapterContainer!: ElementRef;


  constructor(private route: ActivatedRoute, private bookService: BookService, private chapterService: ChapterService) {
  }

  ngOnInit() {
    this.route.queryParams.pipe(
      switchMap(params => {
        this.bookId = params['bookId'];
        this.currentChapter = params['progress'];
        return this.bookService.getBookWithPublishedChapters(this.bookId);
      })
    ).subscribe((book: Book) => {
      this.book = book;
      this.loadChaptersUpToCurrent();

    });
  }

  ngAfterViewInit() {
    this.observe();
  }

  ngAfterViewChecked() {
    this.scrollToCurrentChapter();
  }

  /**
   * Scroll to the current chapter
   */
  scrollToCurrentChapter() {
    // If the current chapter is undefined, do nothing
    if (this.chapters[this.currentChapter] == undefined) {
      return;
    }
    // If the counter is greater than the current chapter, do nothing
    else if(this.counter > this.currentChapter + 1) {
      return;
    }
    // Scroll to the current chapter
    const element = document.getElementById(this.chapters[this.currentChapter].id);
    if(element) {
      element.scrollIntoView();
    }
  }

  /**
   * Load chapters up to the current chapter
   */
  loadChaptersUpToCurrent() {
    if(this.currentChapter == 0) {
      this.getNextChapter();
      return;
    }
    this.chapterService.getNPublishedChaptersByBookId(this.bookId, this.currentChapter + 1).subscribe((chapters: Chapter[]) => {
      this.chapters = chapters;
      this.counter = this.currentChapter;
      this.counter++;
    });


  }

  getNextChapter() {
    // No more chapters
    if(this.counter > this.book.chapterIds.length) {
      return;
    }
    this.chapterService.getChapterById(this.book?.chapterIds[this.counter]).subscribe((chapter: Chapter) => {
      this.chapters.push(chapter);
      this.counter++;
    });
    this.bookService.increaseBookProgress(this.bookId, this.counter + 1).subscribe();

  }


  observe(): void {
    const options = {
      root: null,
      rootMargin: '0px',
      threshold: 0.1
    };
    const observer = new IntersectionObserver((entries, observer) => {
      entries.forEach(entry => {
        if (entry.isIntersecting) {
          console.log('intersecting');
          this.getNextChapter();
        }
      });
    }, options);
    observer.observe(this.chapterContainer.nativeElement);
  }

  getBlocks(chapter: Chapter) {
    return JSON.parse(chapter.content).blocks;
  }
}
