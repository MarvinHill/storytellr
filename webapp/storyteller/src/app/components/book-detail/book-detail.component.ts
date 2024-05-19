import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {BookService} from "../../service/book.service";
import {Book} from "../../model/book";

@Component({
  selector: 'app-book-detail',
  templateUrl: './book-detail.component.html',
  styleUrl: './book-detail.component.scss'
})
export class BookDetailComponent implements OnInit{
  bookId!: string;
  book?: Book;

  constructor(private route: ActivatedRoute, private bookService: BookService, private router: Router) {}

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.bookId = params['bookId'];
    });
    this.getBookById();
  }

  getBookById(): void {
    this.bookService.getBookById(this.bookId).subscribe({
      next: (resp: Book) => {
        this.book = resp;
      },
      error: (error: any) => {
        console.error(error.message);
      }
    });
  }


  navigatetoReadPage() {
    this.router.navigate(['/read'], {queryParams: {bookId: this.bookId}});
  }

  addToLibrary() {

  }

  isBookInLibrary() {

  }

  removeFromLibrary() {

  }
}
