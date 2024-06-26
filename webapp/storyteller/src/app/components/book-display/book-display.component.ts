import { AfterViewChecked, AfterViewInit, Component, ElementRef, EventEmitter, Input, Output, Renderer2, ViewChild } from '@angular/core';
import { Book } from '../../model/book';
import { debounceTime, fromEvent } from 'rxjs';
import { AccessService } from '../../service/access.service';

@Component({
  selector: 'app-book-display',
  templateUrl: './book-display.component.html',
  styleUrl: './book-display.component.scss'
})

/**
 * Component to display a book in Discovery page
 */
export class BookDisplayComponent implements AfterViewInit, AfterViewChecked {


  @Input() book: Book | undefined;
  @Output() imageLoaded = new EventEmitter<HTMLImageElement>();

  @Input() edit : boolean = false;

  @Input() align : "top" | "bottom" = "bottom";

  @Input() placeholder: boolean = false;
  @Input() showDetails: boolean = true;
  @Input() justify: boolean = true;

  @ViewChild("bookContainerWrapper") wrapper: ElementRef | undefined;
  @ViewChild("element") element: ElementRef | undefined;

  constructor(private renderer: Renderer2, private access : AccessService) { }
  ngAfterViewChecked(): void {
    this.recSize();
  }

  ngAfterViewInit(): void {
    this.recSize();
    fromEvent(window, 'resize').pipe(debounceTime(300)).subscribe(() => {
      this.recSize();
    })

  }

  /**
   * Emit the image element to the parent component
   * @param image HTMLImageElement to be emitted
   */
  reemitImageElement(image : HTMLImageElement){
    this.imageLoaded.emit(image);
  }

  /**
   * Open the book details page
   */
  async openBookDetails(){
    if(this.book == null || this.book == undefined){
      return
    }


    if(this.edit){
      this.access.toBookEditPage(this.book);
      return
    }
    this.access.toBookDetailsPage(this.book);

  }

  /**
   * Set the size of the book container
   */
  recSize(){

    if(this.wrapper == undefined || this.element == undefined) return;

    const style = getComputedStyle(this.wrapper.nativeElement);
    const height = this.wrapper.nativeElement.clientHeight - parseInt(style.paddingTop) - parseInt(style.paddingBottom);
    const width = this.wrapper.nativeElement.clientWidth - parseInt(style.paddingLeft) - parseInt(style.paddingBottom);

    const calcHeight = width * 1.6;
    const calcWidth = height / 1.6;

    if(calcHeight > height){
      this.renderer.setStyle(this.element.nativeElement, 'height', `${height}px`);
      this.renderer.setStyle(this.element.nativeElement, 'width', `${calcWidth}px`);
      return
    }

    this.renderer.setStyle(this.element.nativeElement, 'height', `${calcHeight}px`);
    this.renderer.setStyle(this.element.nativeElement, 'width', `${width}px`);
  }


}
