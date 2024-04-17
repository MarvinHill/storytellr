import {AfterViewInit, Component, OnInit} from '@angular/core';
import {KeycloakService} from "keycloak-angular";
import {BookService} from "../../service/book.service";
import {UserService} from "../../service/user.service";
import {Book} from "../../model/book";
import {ActivatedRoute} from "@angular/router";
import {BookMapperService} from "../../service/book-mapper.service";
import {FormBuilderService} from "../../service/form-builder.service";
import {GenreService} from "../../service/genre.service";
import {Genre} from "../../model/genre";

@Component({
  selector: 'app-edit-details',
  templateUrl: './edit-details.component.html',
  styleUrl: './edit-details.component.scss'
})
export class EditDetailsComponent implements OnInit{
  book!: Book;
  bookId!: string;
  titleEdit = false;
  descriptionEdit = false;
  catchphraseEdit= false;
  genreEdit = false;
  genres: Genre[] = [];

  constructor(private keyCloakService: KeycloakService, private bookService: BookService, private userService: UserService, private route: ActivatedRoute,
              private bookMapperService: BookMapperService, private genreService: GenreService) {
  }

  async ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.bookId = params['bookId'];
    });
    this.getBookById(this.bookId);
    this.getGenres();
  }


  getBookById(bookId: string) {
    this.bookService.getBookById(bookId).subscribe({
      next: (resp: Book) => {
        this.book = resp;
      },
      error: (error: any) => {
        console.error(error.message);
      }
    });

  }


  updateTitle() {
    this.titleEdit = !this.titleEdit;
    let editBook = this.bookMapperService.toEditBookRequest(this.book);
    this.bookService.updateBook(editBook).subscribe({
      next: (resp) => {
        console.log("Response:" + resp);
      },
      error: (error) => {
        console.error(error.message);
      }
    });
  }

  updateDescription() {
    this.descriptionEdit = !this.descriptionEdit;
    let editBook = this.bookMapperService.toEditBookRequest(this.book);
    this.bookService.updateBook(editBook).subscribe({
      next: (resp) => {
        console.log("Response:" + resp);
      },
      error: (error) => {
        console.error(error.message);
      }
    });
  }

  updateCatchphrase() {
    this.catchphraseEdit = !this.catchphraseEdit;
    let editBook = this.bookMapperService.toEditBookRequest(this.book);
    this.bookService.updateBook(editBook).subscribe({
      next: (resp) => {
      },
      error: (error) => {
        console.error(error.message);
      }
    });
  }

  getGenres() {
    this.genreService.getAllGenres().subscribe({
      next: (resp: Genre[]) => {
        console.log(resp);
        this.genres = resp;
      },
      error: (error: any) => {
        console.error(error.message);
      }
    });
  }

  updateGenre() {
    this.genreEdit = !this.genreEdit;
    let editBook = this.bookMapperService.toEditBookRequest(this.book);
    this.bookService.updateBook(editBook).subscribe({
      next: (resp) => {
        console.log("Response:" + resp);
      },
      error: (error) => {
        console.error(error.message);
      }
    });
  }

  getGenreName(genreId: string) {
    let genre = this.genres.find(genre => genre.id === genreId);
    return genre?.name;
  }
}
