import {Component, OnInit} from '@angular/core';
import {KeycloakService} from "keycloak-angular";
import {BookService} from "../../service/book.service";
import {UserService} from "../../service/user.service";
import {Book} from "../../model/book";
import {ActivatedRoute} from "@angular/router";
import {BookMapperService} from "../../service/book-mapper.service";
import {FormBuilderService} from "../../service/form-builder.service";

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

  constructor(private keyCloakService: KeycloakService, private bookService: BookService, private userService: UserService, private route: ActivatedRoute,
              private bookMapperService: BookMapperService) {
  }

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
}
