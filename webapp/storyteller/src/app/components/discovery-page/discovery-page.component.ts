import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {BookService} from "../../service/book.service";
import {Book} from "../../model/book";

@Component({
    selector: 'app-discovery-page',
    templateUrl: './discovery-page.component.html',
    styleUrl: './discovery-page.component.scss'
})
export class DiscoveryPageComponent implements OnInit {
    books: Book[] = [];

    constructor(private router: Router, private bookService: BookService) {
    }

    ngOnInit() {
        this.bookService.getBooks().subscribe(books => {
            this.books = books;
        });
    }
}
