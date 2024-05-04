import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {BookService} from "../../service/book.service";
import {Book} from "../../model/book";
import { Genre } from '../../model/genre';
import { GenreService } from '../../service/genre.service';

@Component({
    selector: 'app-discovery-page',
    templateUrl: './discovery-page.component.html',
    styleUrl: './discovery-page.component.scss'
})
export class DiscoveryPageComponent implements OnInit {
    books: Book[] | null = null;
    genres: Genre[] = [];

    pageSize : number = 5;
    currentPage : number = 1;
    mostLikedBooks : Book[] = [];
    displayedBooks : Book[] = [];

    constructor(private router: Router, private bookService: BookService, private genreService: GenreService) {
        this.genreService.getAllGenres().subscribe(genres => {
            this.genres = genres;
        });
    }
    ngOnInit() {
        this.bookService.getBooks().subscribe(books => {
            this.books = books;
            this.mostLikedBooks = books;
            this.pageChanged(1);
        });
    }

    pageChanged(pageNumber : number){
        const start_index = (pageNumber - 1) * this.pageSize;
        this.displayedBooks = this.mostLikedBooks.slice(start_index, start_index + this.pageSize);
    }

    calculatePageCount(){
        return Math.ceil(this.mostLikedBooks?.length / this.pageSize);
    }
}
