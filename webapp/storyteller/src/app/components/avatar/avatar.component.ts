import { Component, Input, OnDestroy, Renderer2 } from '@angular/core';

@Component({
  selector: 'app-avatar',
  templateUrl: './avatar.component.html',
  styleUrl: './avatar.component.scss'
})
export class AvatarComponent implements OnDestroy  {
  @Input() username : string | undefined;
  protected showMenu : boolean = false;
  private removeListener : (() => void) | undefined;

  constructor(private renderer : Renderer2){}


  toggleMenu(event : MouseEvent){
    event.stopPropagation();
    if(this.showMenu && this.removeListener){
      this.removeListener();
    }
    if(!this.removeListener){
      this.removeListener = this.renderer.listen("document", "click", event => {
        console.log(event);
        this.showMenu = false;
      });
    }
    this.showMenu = !this.showMenu;
  }

  ngOnDestroy() {
    if(this.removeListener) this.removeListener();
  }
}
