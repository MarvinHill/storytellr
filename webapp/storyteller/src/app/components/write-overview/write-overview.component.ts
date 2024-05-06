import {Component, OnInit} from '@angular/core';
import {BookService} from "../../service/book.service";
import {AddBookRequest, Book} from "../../model/book";
import {Router} from "@angular/router";
import {KeycloakService} from "keycloak-angular";


@Component({
  selector: 'app-write-overview',
  templateUrl: './write-overview.component.html',
  styleUrl: './write-overview.component.scss'
})
export class WriteOverviewComponent implements OnInit {
  books: Book[] = [];

  constructor(private bookService: BookService, private router: Router, private keycloakService: KeycloakService) {
  }

  ngOnInit() {
    this.bookService.getBooksByAuthor().subscribe((books) => {
      this.books = books;
      console.log(books);
    });
  }

  addBook() {
    const addBook: AddBookRequest = {
      title: 'New Book',
      description: 'New Description',
      genreId: 'New Genre',
      catchphrase: 'New Catchphrase',
      chapterIds: [],
      tags: [],
      public: false,
      adultContent: false,
      commentsDeactivated: false,
      finished: false,
    }
    this.bookService.addBook(addBook).subscribe((book) => {
      console.log(book);
      this.router.navigate(['/edit-details'], {queryParams: {bookId: book.id}});
    });

  }
}
