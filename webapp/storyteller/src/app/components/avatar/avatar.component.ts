import { Component, Input, OnDestroy, Renderer2 } from '@angular/core';

@Component({
  selector: 'app-avatar',
  templateUrl: './avatar.component.html',
  styleUrl: './avatar.component.scss'
})
/**
 * AvatarComponent is a component that displays the user's avatar and username.
 * It allows the user to toggle a dropdown menu to view and edit their profile.
 */
export class AvatarComponent implements OnDestroy  {
  @Input() username : string | undefined;
  protected showMenu : boolean = false;
  private removeListener : (() => void) | undefined;

  constructor(private renderer : Renderer2){}


  /**
   * toggleMenu is a function that toggles the dropdown menu.
   * @param event the event that triggered the function
   */
  toggleMenu(event : MouseEvent){
    event.stopPropagation();
    if(!this.removeListener){
      this.removeListener = this.renderer.listen("document", "click", event => {
        this.showMenu = false;
      });
    }
    this.showMenu = !this.showMenu;
  }

  ngOnDestroy() {
    if(this.removeListener) this.removeListener();
  }
}
