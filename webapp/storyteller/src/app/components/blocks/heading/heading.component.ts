import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-heading',
  templateUrl: './heading.component.html',
  styleUrl: './heading.component.scss'
})
/**
 * HeadingComponent is a component that displays a heading in Reading Mode
 */
export class HeadingComponent {
  @Input() content!: string;

}
