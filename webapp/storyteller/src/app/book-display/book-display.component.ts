import { AfterViewInit, Component, ElementRef, HostListener, Input, OnInit, Renderer2, ViewChild } from '@angular/core';
import { Book } from '../model/book';
import { BehaviorSubject, debounceTime, fromEvent } from 'rxjs';
import { EventManager } from '@angular/platform-browser';
import { Router, RouterStateSnapshot } from '@angular/router';
import { KeycloakService } from 'keycloak-angular';

@Component({
  selector: 'app-book-display',
  templateUrl: './book-display.component.html',
  styleUrl: './book-display.component.scss'
})
export class BookDisplayComponent implements AfterViewInit {


  @Input() book: Book | undefined;
  protected isLoading : boolean = true;

  @ViewChild("bookContainerWrapper") wrapper: ElementRef | undefined;
  @ViewChild("element") element: ElementRef | undefined;
  
  constructor(private renderer: Renderer2, private router: Router, private keycloak : KeycloakService) { }

  ngAfterViewInit(): void {
    console.log("element updated", this.element);
    this.recSize();

    console.log("loaded book", this.book);

    fromEvent(window, 'resize').pipe(debounceTime(300)).subscribe(() => {
      this.recSize();
    })

    
  }

  isLoaded(){
    this.isLoading = false;
  }

  async openBookDetails(){
    if(this.book == null || this.book == undefined){
      console.log("book is null or undefined");
      return
    }

    console.log("current route", window.location.href)

    if(!this.keycloak.isLoggedIn()) this.router.navigate(['/book-details'], {queryParams: {bookId: this.book.id}});

    this.keycloak.loadUserProfile().then((p) => {
      if(p == null || p == undefined || this.book == undefined) return;

      if(p.id == this.book?.author){
        this.router.navigate(['/edit-details'], {queryParams: {bookId: this.book.id}})
        return
      }
      this.router.navigate(['/book-details'], {queryParams: {bookId: this.book.id}})
    });

  }

  recSize(){
    console.log("wrapper", this.wrapper);

    if(this.wrapper == undefined || this.element == undefined) return;
    
    const style = getComputedStyle(this.wrapper.nativeElement);
    const height = this.wrapper.nativeElement.clientHeight - parseInt(style.paddingTop) - parseInt(style.paddingBottom);
    const width = this.wrapper.nativeElement.clientWidth - parseInt(style.paddingLeft) - parseInt(style.paddingBottom);

    const calcHeight = width * 1.6;
    const calcWidth = height / 1.6;

    if(calcHeight > height){
      console.log("calcHeight > height |height|width|aspectRatio",height,calcWidth,String(height / calcWidth));
      this.renderer.setStyle(this.element.nativeElement, 'height', `${height}px`);
      this.renderer.setStyle(this.element.nativeElement, 'width', `${calcWidth}px`);
      return
    }

    console.log("calcHeight < height |height|width|aspectRatio: ",calcHeight,width,String(calcHeight / width));
    this.renderer.setStyle(this.element.nativeElement, 'height', `${calcHeight}px`);
    this.renderer.setStyle(this.element.nativeElement, 'width', `${width}px`);
    console.log("calcHeight:width", calcHeight, width);

  }

  
}
