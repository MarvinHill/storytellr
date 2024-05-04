import { Component, ElementRef, EventEmitter, Input, Output, ViewChild } from '@angular/core';
import { CoverURI } from '../../model/cover';


@Component({
  selector: 'app-lazy-image',
  templateUrl: './lazy-image.component.html',
  styleUrl: './lazy-image.component.scss',
})
export class LazyImageComponent {

  @Output() imageLoaded = new EventEmitter();
  @Output() imgElementLoaded = new EventEmitter<HTMLImageElement>();
  @ViewChild("img") image : ElementRef | undefined;

  @Input() cover : CoverURI | undefined;
  @Input() clickable : boolean = false;
  @Input() size : "lg" | "sm" = "sm";
  @Input() width : string | undefined = "w-32";
  @Input() fullSize : boolean = false;

  imgLoaded() {
    console.log("loaded img", this.getSrc())
    this.imageLoaded.emit();
    this.imgElementLoaded.emit(this.image?.nativeElement);
  }

  getSize() {
    if(this.fullSize) return "";
    return "aspect-[1/1.6] " + this.width;
  }

  getSrc() {
    switch(this.size) {
      case "lg": 
        if(this.cover == undefined) return "/datastore/cover-lg.png";
        if(!this.cover?.lgImageUri.startsWith("/datastore")) return "/datastore/cover-lg.png";
        return this.cover?.lgImageUri;
      case "sm": 
        if(this.cover == undefined) return "/datastore/cover-sm.png";
        if(!this.cover?.lgImageUri.startsWith("/datastore")) return "/datastore/cover-sm.png";
        return this.cover?.smImageUri;
      default:
        return "/datastore/cover-lg.png";
    }
  }

}
