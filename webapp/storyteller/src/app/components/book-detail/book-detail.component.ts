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

  constructor(private route: ActivatedRoute, private bookService: BookService, private router: Router, private libraryService: LibraryService) {}

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.bookId = params['bookId'];
    });
    this.getBookById();
    this.isBookInLibrary();
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


  navigateToReadPage() {
    this.router.navigate(['/read'], {queryParams: {bookId: this.bookId}});
  }

  addToLibrary() {
    this.libraryService.addBookToLibrary(this.bookId).subscribe({
      next: (resp: Book) => {
        console.log("Book added" + resp);
        this.isBookInLibrary();
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
        this.isBookInLibrary();
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
        this.progress = Math.round((resp / totalChapters) * 100);
      },
      error: (error: any) => {
        console.error(error.message);
      }
    });
  }
}
