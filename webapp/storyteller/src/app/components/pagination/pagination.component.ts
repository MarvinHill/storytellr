import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-pagination',
  templateUrl: './pagination.component.html',
  styleUrl: './pagination.component.scss'
})

/**
 * Component to display a pagination
 */
export class PaginationComponent{

  @Output() pageChanged : EventEmitter<number> = new EventEmitter<number>();

  //The page we are currently on
  currentPage : number = 1;
  // Page count is the total number of pages
  @Input() pageCount : number | undefined;
  state : "first" | "last" | "middle" = "first";


  /**
   * Navigate to the previous page
   */
  previousPage(){
    this.state = "middle";
    if(this.currentPage > 1){
      this.currentPage--;
      this.pageChanged.emit(this.currentPage);
    }
    if(this.currentPage <= 1) {
      this.state = "first";
    }
  }

  /**
   * Navigate to the next page
   */
  nextPage(){
    this.state = "middle";
    if(this.pageCount == undefined) return
    if(this.currentPage < this.pageCount){
      this.currentPage++;
      this.pageChanged.emit(this.currentPage);
    }
    if(this.currentPage == this.pageCount) {
      this.state = "last";
    }
  }

}
