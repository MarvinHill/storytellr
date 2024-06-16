import {
  AfterViewChecked,
  AfterViewInit,
  Component,
  ComponentFactoryResolver,
  ElementRef, Injector,
  OnInit,
  ViewChild, ViewContainerRef
} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {BookService} from "../../service/book.service";
import {Book} from "../../model/book";
import {ChapterService} from "../../service/chapter.service";
import {Chapter} from "../../model/chapter";
import {switchMap} from "rxjs";
import {CommentService} from "../../service/comment.service";
import {AddCommentRequest} from "../../model/comment";
import {CommentModalComponent} from "../comment-modal/comment-modal.component";

@Component({
  selector: 'app-read-page',
  templateUrl: './read-page.component.html',
  styleUrl: './read-page.component.scss'
})

/**
 * ReadPageComponent is a component that displays the content of a book chapter by chapter.
 * It allows the user to add comments to each chapter and view comments added by other users.
 */
export class ReadPageComponent implements OnInit, AfterViewInit, AfterViewChecked {
  bookId!: string;
  book!: Book;
  chapters: Chapter[] = [];
  counter: number = 0;
  currentChapter: number = 0;
  scrolling: boolean = true;

  @ViewChild('chapterContainer') chapterContainer!: ElementRef;
  @ViewChild('dynamicComponentContainer', {read: ViewContainerRef})
  modalContainerRef!: ViewContainerRef;

  constructor(private route: ActivatedRoute, private bookService: BookService, private chapterService: ChapterService,
              private commentService: CommentService, private componentFactoryResolver: ComponentFactoryResolver, private injector: Injector,) {
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
   * Scrolls to the current chapter based on the current chapter number
   */
  scrollToCurrentChapter() {
    if (!this.scrolling) {
      return;
    }
    let currentChapter = this.currentChapter;
    currentChapter++;
    // If the current chapter is undefined, do nothing
    if (this.chapters[this.currentChapter] == undefined) {
      return;
    }
    // If the counter is greater than the current chapter, do nothing
    else if (this.counter > currentChapter) {

      return;
    }
    // Scroll to the current chapter
    const element = document.getElementById(this.chapters[this.currentChapter].id);
    if (element) {
      element.scrollIntoView();
      this.scrolling = false;
    }
  }

  /**
   * Load chapters up to the current chapter
   */
  loadChaptersUpToCurrent() {
    if (this.currentChapter == 0) {
      this.getNextChapter();
      return;
    }
    let n = this.currentChapter;
    n++;
    this.chapterService.getNPublishedChaptersByBookId(this.bookId, n).subscribe((chapters: Chapter[]) => {
      this.chapters = chapters;
      this.counter = this.currentChapter;
      this.counter++;
    });


  }

  /**
   * Get the next chapter of the book
   */
  getNextChapter() {
    // No more chapters
    console.log("Counter in next " + this.counter);

    if (this.counter > this.book.chapterIds.length) {
      console.log("No more chapters");
      return;
    }
    this.chapterService.getChapterById(this.book?.chapterIds[this.counter]).subscribe((chapter: Chapter) => {
      this.chapters.push(chapter);
    });
    this.bookService.increaseBookProgress(this.bookId, this.counter).subscribe();
    this.counter++;

  }


  /**
   * Observe the chapter container for intersection to dynamically load chapters
   */
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

  /**
   * Get the blocks of a chapter
   * @param chapter the chapter which blocks should be loaded
   */
  getBlocks(chapter: Chapter) {
    return JSON.parse(chapter.content).blocks;
  }

  /**
   * Add a comment to a chapter
   * @param comment the comment to be added
   */
  addComment(comment: AddCommentRequest) {
    this.commentService.addComment(comment).subscribe(
      {
        next: (comment) => {
          console.log(comment);
        },
        error: (error: any) => {
          console.error(error.message);
        }
      }
    )
  }

  /**
   * Open the comment modal to show and add comments to a block
   * @param chapterId the id of the chapter containing the block
   * @param blockId the id of the block
   */
  openCommentModal(chapterId: string, blockId: string) {
    // Create the component dynamically
    const commentModalRef = this.modalContainerRef.createComponent(CommentModalComponent, {
      injector: this.injector,
    });

    // Set the input properties
    commentModalRef.instance.chapterId = chapterId;
    commentModalRef.instance.blockId = blockId;

    // Subscribe to the event
    commentModalRef.instance.addCommentEvent.subscribe((event: AddCommentRequest) => {
      this.addComment(event);
    });
  }


}
