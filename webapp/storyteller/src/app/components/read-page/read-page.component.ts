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
        return this.bookService.getBookById(this.bookId);
      })
    ).subscribe((book: Book) => {
      this.book = book;
      this.getNextChapter();

    });
  }

  ngAfterViewInit() {
    this.observe();
  }

  // getPublishedChapters(book: Book) {
  //   this.chapterService.getPublishedChaptersByBookId(book.id).pipe(
  //     switchMap((chapters: Chapter[]) => {
  //       this.chapters = chapters;
  //       return chapters;
  //     })
  //   ).subscribe((chapter: Chapter) => {
  //     this.chapter = chapter;
  //     this.loadChapter(this.counter)
  //   });
  // }

  loadChapter(counter: number) {
    // this.chapter = this.chapters[counter];
    // JSON.parse(this.chapter.content).blocks.forEach((block: any) => {
    //   this.blocks.push(block);
    // });
    // console.log(this.blocks);
  }

  getNextChapter() {
    // No more chapters
    if(this.counter > this.book.chapterIds.length) {
      return;
    }
    this.chapterService.getChapterById(this.book?.chapterIds[this.counter]).subscribe((chapter: Chapter) => {
      // Skip unpublished chapters
      if(!chapter.published) {
        this.counter++;
        this.getNextChapter();
      }
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
