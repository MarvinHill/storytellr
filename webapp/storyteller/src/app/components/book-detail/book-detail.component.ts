import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {BookService} from "../../service/book.service";
import {Book} from "../../model/book";

@Component({
  selector: 'app-book-detail',
  templateUrl: './book-detail.component.html',
  styleUrl: './book-detail.component.scss'
})
export class BookDetailComponent implements OnInit{
  private bookId!: string;
  private book!: Book;

  constructor(private route: ActivatedRoute, private bookService: BookService) {}
  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.bookId = params['bookId'];
    });
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




}
