import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-pagination',
  templateUrl: './pagination.component.html',
  styleUrl: './pagination.component.scss'
})
export class PaginationComponent{

  @Output() pageChanged : EventEmitter<number> = new EventEmitter<number>();

  currentPage : number = 1;
  @Input() pageCount : number | undefined;
  state : "first" | "last" | "middle" = "first";


  previousePage(){
    this.state = "middle";
    if(this.currentPage > 1){
      this.currentPage--;
      this.pageChanged.emit(this.currentPage);
    }
    if(this.currentPage <= 1) {
      this.state = "first";
    }
  }

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
