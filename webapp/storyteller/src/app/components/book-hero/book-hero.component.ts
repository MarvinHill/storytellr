
import { AfterViewInit, Component, ElementRef, Input, ViewChild } from '@angular/core';
import { Book } from '../../model/book';
import { BookDisplayComponent } from '../book-display/book-display.component';
import { Palette } from 'auto-palette';
import { AccessService } from '../../service/access.service';

@Component({
  selector: 'app-book-hero',
  templateUrl: './book-hero.component.html',
  styleUrl: './book-hero.component.scss'
})

/**
 * Component to display a book in Hero section
 */
export class BookHeroComponent implements AfterViewInit {

  @Input() book: Book | undefined;

  primaryColor = ""
  secondaryColor = ""

  @ViewChild("element") element: ElementRef | undefined;

  @ViewChild("c1") c1: ElementRef | undefined;
  @ViewChild("c2") c2: ElementRef | undefined;
  @ViewChild("c3") c3: ElementRef | undefined;
  @ViewChild("c4") c4: ElementRef | undefined;
  @ViewChild("c5") c5: ElementRef | undefined;
  @ViewChild("c6") c6: ElementRef | undefined;

  @ViewChild("cover") cover: ElementRef | undefined;

  constructor(private access : AccessService){ }

  ngAfterViewInit(): void {
    const elements = [this.c1, this.c2, this.c3, this.c4, this.c5, this.c6];

    for (const element of elements) {
      let dimension = Math.random() * 15;
      if(dimension < 5) dimension = 5;
      element?.nativeElement?.setAttribute("style", `left: ${Math.random() * 100}%; top: ${Math.random() * 100}%; width: ${dimension}em; height: ${dimension}em;`);
    }
  }

  /**
   * Update the primary and secondary colors of the book for the background
   * @param img HTMLImageElement to extract colors from
   */
  async updateColors(img : HTMLImageElement){

    if(img == null) return;

    const palette = Palette.extract(img, {maxSwatches: 2});
    const swatches = palette.findSwatches(2, "light");
    

    this.primaryColor = String(`rgb(${swatches[0].color.toRGB().r}, ${swatches[0].color.toRGB().g}, ${swatches[0].color.toRGB().b})`);
    console.log("primary color", this.primaryColor)
    this.secondaryColor = String(`rgb(${swatches[1].color.toRGB().r}, ${swatches[1].color.toRGB().g}, ${swatches[1].color.toRGB().b})`);
    console.log("secondary color", this.secondaryColor)
  }

  /**
   * Open the book details page
   */
  openBookDetails(){
    if(this.book == null || this.book == undefined) return;
    this.access.toBookDetailsPage(this.book);
  }

}
