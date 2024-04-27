
import { AfterViewInit, Component, ElementRef, Input, ViewChild } from '@angular/core';
import { Book } from '../model/book';
import { BookDisplayComponent } from '../book-display/book-display.component';
import ColorThief from 'colorthief'

@Component({
  selector: 'app-book-hero',
  templateUrl: './book-hero.component.html',
  styleUrl: './book-hero.component.scss'
})
export class BookHeroComponent implements AfterViewInit {

  @Input() book: Book | undefined;

  mainColor = ""

  @ViewChild("element") element: ElementRef | undefined;

  @ViewChild("c1") c1: ElementRef | undefined;
  @ViewChild("c2") c2: ElementRef | undefined;
  @ViewChild("c3") c3: ElementRef | undefined;
  @ViewChild("c4") c4: ElementRef | undefined;
  @ViewChild("c5") c5: ElementRef | undefined;
  @ViewChild("c6") c6: ElementRef | undefined;

  ngAfterViewInit(): void {
    const elements = [this.c1, this.c2, this.c3, this.c4, this.c5, this.c6];

    for (const element of elements) {
      let dimension = Math.random() * 15;
      if(dimension < 5) dimension = 5;
      element?.nativeElement?.setAttribute("style", `left: ${Math.random() * 100}%; top: ${Math.random() * 100}%; width: ${dimension}em; height: ${dimension}em;`);
    }
  }

  updateColors(){

  }
  

}
