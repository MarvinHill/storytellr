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

  updateView() {
    this.getBookById();
    this.isBookInLibrary();
    this.isBookLiked();
  }


  navigateToReadPage() {
    this.router.navigate(['/read'], {queryParams: {bookId: this.bookId, progress: this.readChapter}});
  }

  addToLibrary() {
    this.libraryService.addBookToLibrary(this.bookId).subscribe({
      next: (resp: Book) => {
        console.log("Book added" + resp);
        this.updateView();
      },
      error: (error: any) => {
        console.error(error.message);
      }
    });
  }

  isBookInLibrary(){
    this.libraryService.isBookInLibrary(this.bookId).subscribe({
      next: (resp: boolean) => {
        console.log("Book in library: " + resp);
        this.bookInLibrary = resp;
      },
      error: (error: any) => {
        console.error(error.message);
      }
    });
    }

  removeFromLibrary() {
    this.libraryService.removeBookFromLibrary(this.bookId).subscribe({
      next: (resp: Book) => {
        console.log(resp);
        this.updateView();
      },
      error: (error: any) => {
        console.error(error.message);
      }
    });
  }

  likeBook() {
    this.libraryService.likeBook(this.bookId).subscribe({
      next: (resp: Book) => {
        console.log(resp);
        this.updateView();
      },
      error: (error: any) => {
        console.error(error.message);
      }
    });
  }

  unlikeBook() {
    this.libraryService.unlikeBook(this.bookId).subscribe({
      next: (resp: Book) => {
        console.log(resp);
        this.updateView();
      },
      error: (error: any) => {
        console.error(error.message);
      }
    });
  }

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

  getBookProgress() {
    this.bookService.getBookProgress(this.bookId).subscribe({
      next: (resp: number) => {
        let totalChapters = this.book.chapterIds.length;
        this.readChapter = resp;
        this.progress = Math.round((this.readChapter / totalChapters) * 100);
        // If progress is Nan, set it to 0
        if(isNaN(this.progress)) {
          this.progress = 0;
        }
      },
      error: (error: any) => {
        console.error(error.message);
      }
    });
  }
}
