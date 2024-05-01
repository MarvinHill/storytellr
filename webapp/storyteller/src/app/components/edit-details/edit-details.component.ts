import {Component, OnInit} from '@angular/core';
import {BookService} from "../../service/book.service";
import {Book} from "../../model/book";
import {ActivatedRoute} from "@angular/router";
import {BookMapperService} from "../../service/book-mapper.service";
import {GenreService} from "../../service/genre.service";
import {Genre} from "../../model/genre";
import { CoverURI } from '../../model/cover';
import { CoverService } from '../../service/cover.service';

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
  tagsEdit = false;
  genres: Genre[] = [];
  newTags!: string;

  constructor(private coverService: CoverService, private bookService: BookService, private route: ActivatedRoute,
              private bookMapperService: BookMapperService, private genreService: GenreService) {
  }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.bookId = params['bookId'];
    });
    this.getBookById(this.bookId);
    this.getGenres();
  }

  openCoverUpload() {
      this.coverService.show()
  }

  updateCover(url : CoverURI) {
    console.log("updateCoverEvent",url);
    this.book.cover = url;
  }


  getBookById(bookId: string) {
    this.bookService.getBookById(bookId).subscribe({
      next: (resp: Book) => {
        this.book = resp;
        this.newTags = resp.tags.join(" ");
      },
      error: (error: any) => {
        alert(error.message);
      }
    });

  }


  updateTitle() {
    this.titleEdit = false;
    let editBook = this.bookMapperService.toEditBookRequest(this.book);
    this.bookService.updateBook(editBook).subscribe({
      next: (resp) => {
        console.log("Response:" + resp);
      },
      error: (error) => {
        alert(error.message);
        this.getBookById(this.bookId);
      }
    });

  }

  updateDescription() {
    this.descriptionEdit = false;
    let editBook = this.bookMapperService.toEditBookRequest(this.book);
    this.bookService.updateBook(editBook).subscribe({
      next: (resp) => {
        console.log("Response:" + resp);
      },
      error: (error) => {
        alert(error.message);
        this.getBookById(this.bookId);
      }
    });
  }

  updateCatchphrase() {
    this.catchphraseEdit = false;
    let editBook = this.bookMapperService.toEditBookRequest(this.book);
    this.bookService.updateBook(editBook).subscribe({
      next: (resp) => {
        console.log("Response:" + resp);
      },
      error: (error) => {
        alert(error.message);
        this.getBookById(this.bookId)
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
    let editBook = this.bookMapperService.toEditBookRequest(this.book);
    this.bookService.updateBook(editBook).subscribe({
      next: (resp) => {
        console.log("Response:" + resp);
      },
      error: (error) => {
        alert(error.message);
        this.getBookById(this.bookId)
      }
    });
  }

  getTagsFromInput(tags: string) : string[] {
    return tags.split(" ").map(tag => tag.trim());
  }

  updateTags() {
    this.tagsEdit = false;
    this.book.tags = this.getTagsFromInput(this.newTags);
    console.log(this.book.tags);
    let editBook = this.bookMapperService.toEditBookRequest(this.book);
    this.bookService.updateBook(editBook).subscribe({
      next: (resp) => {
        console.log("Response:" + resp);
      },
      error: (error) => {
        alert(error.message);
        this.getBookById(this.bookId)
      }
    });
  }
}
