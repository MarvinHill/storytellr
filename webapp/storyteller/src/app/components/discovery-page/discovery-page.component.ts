import { Component } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-discovery-page',
  templateUrl: './discovery-page.component.html',
  styleUrl: './discovery-page.component.scss'
})
export class DiscoveryPageComponent {
  private bookId : string = '1234567890';

  constructor(private router: Router) {}

  navigateToBookDetail() {
    this.router.navigate(['/book-details'], { queryParams: { bookId: this.bookId } })
  }

  navigateToEditDetail() {
    this.router.navigate(['/edit-details'])
  }
}
