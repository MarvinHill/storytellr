import {Component, OnInit} from '@angular/core';
import {KeycloakService} from "keycloak-angular";
import {BookService} from "../../service/book.service";
import {UserServiceService} from "../../service/user-service.service";
import {Book} from "../../model/book";
import {ActivatedRoute} from "@angular/router";
import {FormBuilderService} from "../../service/form-builder.service";
import {FormGroup} from "@angular/forms";

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

  constructor(private keyCloakService: KeycloakService, private bookService: BookService, private userService: UserServiceService, private route: ActivatedRoute) {
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

}
