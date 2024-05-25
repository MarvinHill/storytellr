import {Component, OnInit} from '@angular/core';
import {Book} from "../../model/book";
import {LibraryService} from "../../service/library.service";

@Component({
  selector: 'app-library',
  templateUrl: './library.component.html',
  styleUrl: './library.component.scss'
})
export class LibraryComponent implements OnInit{
  books: Book[] = [];

  constructor(private libraryService: LibraryService) {}

  ngOnInit(): void {
    this.libraryService.getLibrary().subscribe({
      next: (resp: Book[]) => {
        this.books = resp;
      },
      error: (error: any) => {
        console.error(error.message);
      }
    });
  }

}
