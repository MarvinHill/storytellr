import { AfterContentChecked, AfterViewChecked, AfterViewInit, Component, ElementRef, EventEmitter, HostListener, Input, OnInit, Output, Renderer2, ViewChild } from '@angular/core';
import { Book } from '../../model/book';
import { BehaviorSubject, debounceTime, fromEvent } from 'rxjs';
import { EventManager } from '@angular/platform-browser';
import { Router, RouterStateSnapshot } from '@angular/router';
import { KeycloakService } from 'keycloak-angular';
import { AccessService } from '../../service/access.service';

@Component({
  selector: 'app-book-display',
  templateUrl: './book-display.component.html',
  styleUrl: './book-display.component.scss'
})
export class BookDisplayComponent implements AfterViewInit, AfterViewChecked {


  @Input() book: Book | undefined;
  @Output() imageLoaded = new EventEmitter<HTMLImageElement>();

  @Input() showDetails: boolean = true;

  @ViewChild("bookContainerWrapper") wrapper: ElementRef | undefined;
  @ViewChild("element") element: ElementRef | undefined;
  
  constructor(private renderer: Renderer2, private access : AccessService) { }
  ngAfterViewChecked(): void {
    this.recSize();
  }

  ngAfterViewInit(): void {
    console.log("element updated", this.element);
    this.recSize();

    console.log("loaded book", this.book);

    fromEvent(window, 'resize').pipe(debounceTime(300)).subscribe(() => {
      this.recSize();
    })

  }

  reemitImageElement(image : HTMLImageElement){
    this.imageLoaded.emit(image);
  }

  async openBookDetails(){
    if(this.book == null || this.book == undefined){
      console.log("book is null or undefined");
      return
    }

    console.log("current route", window.location.href)

    this.access.toEditOrDetailPage(this.book);

  }

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
