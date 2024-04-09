import { Component } from '@angular/core';

@Component({
  selector: 'app-top-bar',
  templateUrl: './top-bar.component.html',
  styleUrl: './top-bar.component.scss'
})
export class TopBarComponent {

  toggleMenu() {
    const burgerMenu = document.getElementById("burgerMenu");
    console.log(burgerMenu);
    if(burgerMenu){
      burgerMenu.classList.toggle("hidden");
      console.log(burgerMenu.classList)
    }

  }
}
