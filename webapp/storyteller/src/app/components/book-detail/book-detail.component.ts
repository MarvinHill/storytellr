import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {BookService} from "../../service/book.service";
import {Book} from "../../model/book";
import {LibraryService} from "../../service/library.service";

@Component({
  selector: 'app-book-detail',
  templateUrl: './book-detail.component.html',
  styleUrl: './book-detail.component.scss'
})

/**
 * BookDetailComponent is a component that displays the details of a book
 */
export class BookDetailComponent implements OnInit{
  bookId!: string;
  book!: Book;
  bookInLibrary: boolean = false;
  progress: number = 0;
  readChapter: number = 0;
  bookLiked: boolean = false;

  constructor(private route: ActivatedRoute, private bookService: BookService, private router: Router, private libraryService: LibraryService) {}

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.bookId = params['bookId'];
    });
    this.updateView();
  }

  /**
   * getBookById is a function that retrieves a book by its ID
   */
  getBookById(): void {
    this.bookService.getBookWithPublishedChapters(this.bookId).subscribe({
      next: (resp: Book) => {
        this.book = resp;
        this.getBookProgress();
      },
      error: (error: any) => {
        console.error(error.message);
      }
    });
  }

  /**
   * updateView is a function that updates the view of the book detail page
   */
  updateView() {
    this.getBookById();
    this.isBookInLibrary();
    this.isBookLiked();
  }


  /**
   * navigateToReadPage is a function that navigates to the read page
   */
  navigateToReadPage() {
    this.router.navigate(['/read'], {queryParams: {bookId: this.bookId, progress: this.readChapter}});
  }

  /**
   * addToLibrary is a function that adds a book to the library
   */
  addToLibrary() {
    this.libraryService.addBookToLibrary(this.bookId).subscribe({
      next: (resp: Book) => {
        this.updateView();
      },
      error: (error: any) => {
        console.error(error.message);
      }
    });
  }

  /**
   * isBookInLibrary is a function that checks if a book is in the users library
   */
  isBookInLibrary(){
    this.libraryService.isBookInLibrary(this.bookId).subscribe({
      next: (resp: boolean) => {
        this.bookInLibrary = resp;
      },
      error: (error: any) => {
        console.error(error.message);
      }
    });
    }

  /**
   * removeFromLibrary is a function that removes a book from the users library
   */
  removeFromLibrary() {
    this.libraryService.removeBookFromLibrary(this.bookId).subscribe({
      next: (resp: Book) => {
        this.updateView();
      },
      error: (error: any) => {
        console.error(error.message);
      }
    });
  }

  /**
   * likeBook is a function that likes a book
   */
  likeBook() {
    this.libraryService.likeBook(this.bookId).subscribe({
      next: (resp: Book) => {
        this.updateView();
      },
      error: (error: any) => {
        console.error(error.message);
      }
    });
  }

  /**
   * unlikeBook is a function that unlikes a book
   */
  unlikeBook() {
    this.libraryService.unlikeBook(this.bookId).subscribe({
      next: (resp: Book) => {
        this.updateView();
      },
      error: (error: any) => {
        console.error(error.message);
      }
    });
  }

  /**
   * isBookLiked is a function that checks if a book is liked by the user
   */
  isBookLiked() {
    this.libraryService.isBookLiked(this.bookId).subscribe({
      next: (resp: boolean) => {
        this.bookLiked = resp;
      },
      error: (error: any) => {
        console.error(error.message);
      }
    });
  }

  /**
   * getBookProgress is a function that gets the progress of the book
   */
  getBookProgress() {
    this.bookService.getBookProgress(this.bookId).subscribe({
      next: (resp: number) => {
        let totalChapters = this.book.chapterIds.length;
        this.readChapter = resp;
        if(totalChapters === 0) {
          this.progress = 0;
          return;
        }
        this.progress = Math.round((this.readChapter / totalChapters) * 100);
        // If progress is Nan, set it to 0
        if(isNaN(this.progress) || this.progress === Infinity || !this.progress ) {
          this.progress = 0;
        }
        else if(this.progress > 100) {
          this.progress = 100;
        }
      },
      error: (error: any) => {
        console.error(error.message);
      }
    });
  }
}
