import {Component, OnInit} from '@angular/core';
import {BookService} from "../../service/book.service";
import {AddBookRequest, Book} from "../../model/book";
import {Router} from "@angular/router";
import {KeycloakService} from "keycloak-angular";
import {GenreService} from "../../service/genre.service";
import {Genre} from "../../model/genre";


@Component({
  selector: 'app-write-overview',
  templateUrl: './write-overview.component.html',
  styleUrl: './write-overview.component.scss'
})
export class WriteOverviewComponent implements OnInit {
  books: Book[] = [];
  genre!: Genre;

  constructor(private bookService: BookService, private router: Router, private keycloakService: KeycloakService,
              private genreService: GenreService) {
  }

  ngOnInit() {
    this.bookService.getBooksByAuthor().subscribe((books) => {
      this.books = books;
      console.log(books);
    });
    this.getGenre();
  }

  getGenre(){
    this.genreService.getGenres().subscribe((genres) => {
      this.genre =  genres[0];
    });
  }

  addBook() {
    const addBook: AddBookRequest = {
      title: 'New Book',
      description: 'New Description',
      genreId: this.genre.id,
      catchphrase: '',
      chapterIds: [],
      tags: ["new"],
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
