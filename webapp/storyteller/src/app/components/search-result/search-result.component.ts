import { AfterViewInit, Component, ElementRef, Input, ViewChild } from '@angular/core';
import { AccessService } from '../../service/access.service';

@Component({
  selector: 'app-search-result',
  templateUrl: './search-result.component.html',
  styleUrl: './search-result.component.scss'
})
export class SearchResultComponent{

  @Input() book : any;

  private radomTilt : number = Math.random() * 4 - 2;
  private randomTransfromY : number = Math.random() * 4 - 2;
  private randomTransfromX : number = Math.random() * 1 - 2;

  constructor(private access: AccessService){}



  genrateTransform() {

    return `transform: rotate(${this.radomTilt}deg) translate(${this.randomTransfromX}rem, ${this.randomTransfromY}rem;`;
  }

  openBookDetails(){
    if(this.book == null || this.book == undefined) return;
    this.access.toBookDetailsPage(this.book);
  }
  

}
