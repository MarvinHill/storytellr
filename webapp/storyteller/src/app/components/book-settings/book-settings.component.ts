import {Component, OnInit, Input, AfterContentInit} from '@angular/core';
import {FormGroup} from "@angular/forms";
import {FormBuilderService} from "../../service/form-builder.service";
import {Book} from "../../model/book";
import {BookService} from "../../service/book.service";
import {BookMapperService} from "../../service/book-mapper.service";


@Component({
  selector: 'app-book-settings',
  templateUrl: './book-settings.component.html',
  styleUrl: './book-settings.component.scss'
})

/**
 * Component to display the settings of a book
 */
export class BookSettingsComponent implements OnInit, AfterContentInit{
  settingsForm!: FormGroup;
  @Input() book!: Book;


  constructor(private formBuilderService: FormBuilderService, private bookService: BookService, private bookMapperService: BookMapperService) {
  }

  ngOnInit() {
    this.settingsForm = this.formBuilderService.buildBookSettingsForm();
  }

  ngAfterContentInit() {
    this.settingsForm.get('public')?.setValue(this.book.public);
    this.settingsForm.get('adultContent')?.setValue(this.book.adultContent);
    this.settingsForm.get('finished')?.setValue(this.book.finished);
    this.settingsForm.get('commentsDeactivated')?.setValue(this.book.commentsDeactivated);
  }

  /**
   * Update the public status of the book
   */
  updatePublic() {
    this.book.public = this.settingsForm.get('public')?.value;
    this.bookMapperService.toEditBookRequest(this.book);
    this.bookService.updateBook(this.book).subscribe({
      next: (resp) => {
      },
      error: (error) => {
        console.error(error.message);
      }
    });
  }

  /**
   * Update the adult content status of the book
   */
  updateAdultContent() {
    this.book.adultContent = this.settingsForm.get('adultContent')?.value;
    this.bookMapperService.toEditBookRequest(this.book);
    this.bookService.updateBook(this.book).subscribe({
      next: (resp) => {
      },
      error: (error) => {
        console.error(error.message);
      }
    });
  }

  /**
   * Update the finished status of the book
   */
  updateFinished() {
    this.book.finished = this.settingsForm.get('finished')?.value;
    this.bookMapperService.toEditBookRequest(this.book);
    this.bookService.updateBook(this.book).subscribe({
      next: (resp) => {
      },
      error: (error) => {
        console.error(error.message);
      }
    });
  }

  /**
   * Update the comments deactivated status of the book
   */
  updateCommentsDeactivated() {
    this.book.commentsDeactivated = this.settingsForm.get('commentsDeactivated')?.value;
    this.bookMapperService.toEditBookRequest(this.book);
    this.bookService.updateBook(this.book).subscribe({
      next: (resp) => {
      },
      error: (error) => {
        console.error(error.message);
      }
    });
  }
}
