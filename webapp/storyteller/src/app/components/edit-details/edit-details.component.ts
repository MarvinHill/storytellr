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

/**
 * Component for editing the details of a book
 */
export class EditDetailsComponent implements OnInit{
  book!: Book;
  bookId!: string;
  titleEdit = false;
  descriptionEdit = false;
  catchphraseEdit= false;
  tagsEdit = false;
  genres: Genre[] = [];
  newTags!: string;
  showErrorTitle = false;
  showErrorDescription = false;

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

  /**
   * Opens the cover upload dialog.
   */
  openCoverUpload() {
      this.coverService.show()
  }

  /**
   * Updates the cover of the book.
   * @param url The new cover URI.
   */
  updateCover(url : CoverURI) {
    this.book.cover = url;
  }


  /**
   * Gets the book by its id.
   * @param bookId The id of the book.
   */
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


  /**
   * Updates the title of the book.
   */
  updateTitle() {
    if(this.book.title.trim() == "" || this.book.title == null ) {
      this.showErrorTitle = true;
      return;
    }
    this.showErrorTitle = false;
    this.titleEdit = false;
    let editBook = this.bookMapperService.toEditBookRequest(this.book);
    this.bookService.updateBook(editBook).subscribe({
      next: (resp) => {},
      error: (error) => {
        alert(error.message);
        this.getBookById(this.bookId);
      }
    });

  }

  /**
   * Updates the description of the book.
   */
  updateDescription() {
    if(this.book.description.trim() == "" || this.book.description == null ) {
      this.showErrorDescription = true;
      return;
    }
    this.showErrorDescription = false;
    this.descriptionEdit = false;
    let editBook = this.bookMapperService.toEditBookRequest(this.book);
    this.bookService.updateBook(editBook).subscribe({
      next: (resp) => {},
      error: (error) => {
        alert(error.message);
        this.getBookById(this.bookId);
      }
    });
  }

  /**
   * Updates the catchphrase of the book.
   */
  updateCatchphrase() {
    this.catchphraseEdit = false;
    let editBook = this.bookMapperService.toEditBookRequest(this.book);
    this.bookService.updateBook(editBook).subscribe({
      next: (resp) => {},
      error: (error) => {
        alert(error.message);
        this.getBookById(this.bookId)
      }
    });
  }

  /**
   * Gets all genres.
   */
  getGenres() {
    this.genreService.getAllGenres().subscribe({
      next: (resp: Genre[]) => {
        this.genres = resp;
      },
      error: (error: any) => {
        console.error(error.message);
      }
    });
  }

  /**
   * Updates the genre of the book.
   */
  updateGenre() {
    let editBook = this.bookMapperService.toEditBookRequest(this.book);
    this.bookService.updateBook(editBook).subscribe({
      next: (resp) => {},
      error: (error) => {
        alert(error.message);
        this.getBookById(this.bookId)
      }
    });
  }

  /**
   * Gets the tags from the input.
   * @param tags The tags as a string.
   */
  getTagsFromInput(tags: string) : string[] {
    return tags.split(" ").map(tag => tag.trim());
  }

  /**
   * Updates the tags of the book.
   */
  updateTags() {
    this.tagsEdit = false;
    this.book.tags = this.getTagsFromInput(this.newTags);
    let editBook = this.bookMapperService.toEditBookRequest(this.book);
    this.bookService.updateBook(editBook).subscribe({
      next: (resp) => {},
      error: (error) => {
        alert(error.message);
        this.getBookById(this.bookId)
      }
    });
  }
}
