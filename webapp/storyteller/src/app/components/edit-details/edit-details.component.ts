import {Component, OnInit} from '@angular/core';
import {KeycloakService} from "keycloak-angular";
import {BookService} from "../../service/book.service";
import {UserServiceService} from "../../service/user-service.service";
import {Book} from "../../model/book";

@Component({
  selector: 'app-edit-details',
  templateUrl: './edit-details.component.html',
  styleUrl: './edit-details.component.scss'
})
export class EditDetailsComponent implements OnInit{
  book?: Book;
  titleEdit = false;

  constructor(private keyCloakService: KeycloakService, private bookService: BookService, private userService: UserServiceService) {
  }

  ngOnInit() {
    this.keyCloakService.getToken()
      .then(token => {
        console.log(this.userService.getUserIdFromJWT(token));
      });
  }

}
