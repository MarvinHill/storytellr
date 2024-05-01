import { Component, Input } from '@angular/core';
import { Book } from '../../model/book';

@Component({
  selector: 'app-book-showcase',
  templateUrl: './book-showcase.component.html',
  styleUrl: './book-showcase.component.scss'
})
export class BookShowcaseComponent {
  @Input() clipMode : "rect" | "circle" = "rect";
  @Input() book : Book | undefined;
}
