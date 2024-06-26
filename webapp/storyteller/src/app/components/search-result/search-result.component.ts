import { Component, Input } from '@angular/core';
import { AccessService } from '../../service/access.service';

@Component({
  selector: 'app-search-result',
  templateUrl: './search-result.component.html',
  styleUrl: './search-result.component.scss'
})

/**
 * Component to display a book in the search results
 */
export class SearchResultComponent{

  @Input() book : any;

  private randomTilt : number = Math.random() * 4 - 2;
  private randomTransformY : number = Math.random() * 4 - 2;
  private randomTransformX : number = Math.random() * 1 - 2;

  constructor(private access: AccessService){}


  /**
   * Generate a random transform for the book cover
   */
  generateTransform() {
    return `transform: rotate(${this.randomTilt}deg) translate(${this.randomTransformX}rem, ${this.randomTransformY}rem;`;
  }

  /**
   * Open the book details page
   */
  openBookDetails(){
    if(this.book == null || this.book == undefined) return;
    this.access.toBookDetailsPage(this.book);
  }
  

}
