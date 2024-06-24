import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {BookService} from "../../service/book.service";
import {Book} from "../../model/book";
import { Genre } from '../../model/genre';
import { GenreService } from '../../service/genre.service';
import {LibraryService} from "../../service/library.service";
import {Poll, PollOption} from "../../model/poll";

@Component({
    selector: 'app-discovery-page',
    templateUrl: './discovery-page.component.html',
    styleUrl: './discovery-page.component.scss'
})
export class DiscoveryPageComponent implements OnInit {
    books: Book[] | null = null;
    genres: Genre[] = [];
    libraryBooks: Book[] = [];

    pageSize : number = 5;
    currentPage : number = 1;
    mostLikedBooks : Book[] = [];
    displayedBooks : Book[] = [];

    constructor(private router: Router, private bookService: BookService, private genreService: GenreService, private libraryService: LibraryService) {
        this.genreService.getAllGenres().subscribe(genres => {
            this.genres = genres;
        });
    }
    ngOnInit() {
        this.libraryService.getRandomBooks().subscribe(books => {
            this.libraryBooks = books;
            this.pageChanged(1);
        });
        this.bookService.getBooks().subscribe(books => {
            this.mostLikedBooks = books;
            this.pageChanged(1);
        });
        this.bookService.getBooks().subscribe({
            next: (resp: Book[]) => {
                this.books = resp;
                this.pageChanged(1);
            },
            error: (error: any) => {
                console.error(error.message);
            }
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
