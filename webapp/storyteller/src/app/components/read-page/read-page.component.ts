import {AfterViewInit, ChangeDetectorRef, Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {BookService} from "../../service/book.service";
import {Book} from "../../model/book";
import {ChapterService} from "../../service/chapter.service";
import {Chapter} from "../../model/chapter";
import {of, switchMap} from "rxjs";

@Component({
  selector: 'app-read-page',
  templateUrl: './read-page.component.html',
  styleUrl: './read-page.component.scss'
})
export class ReadPageComponent implements OnInit, AfterViewInit {
  bookId!: string;
  book!: Book;
  chapters: Chapter[] = [];
  counter: number = 0;

  @ViewChild('chapterContainer') chapterContainer!: ElementRef;


  constructor(private route: ActivatedRoute, private bookService: BookService, private chapterService: ChapterService) {
  }

  ngOnInit() {
    this.route.queryParams.pipe(
      switchMap(params => {
        this.bookId = params['bookId'];
        return this.bookService.getBookWithPublishedChapters(this.bookId);
      })
    ).subscribe((book: Book) => {
      this.book = book;
      this.getNextChapter();

    });
  }

  ngAfterViewInit() {
    this.observe();
  }

  getNextChapter() {
    // No more chapters
    if(this.counter > this.book.chapterIds.length) {
      return;
    }
    this.chapterService.getChapterById(this.book?.chapterIds[this.counter]).subscribe((chapter: Chapter) => {
      this.chapters.push(chapter);
      console.log(this.chapters);
      this.counter++;
    });
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
