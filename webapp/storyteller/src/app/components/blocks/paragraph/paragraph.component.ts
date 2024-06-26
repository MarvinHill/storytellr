import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-paragraph',
  templateUrl: './paragraph.component.html',
  styleUrl: './paragraph.component.scss'
})
/**
 * ParagraphComponent is a component that displays a paragraph in Reading Mode
 */
export class ParagraphComponent {
  @Input() content!: string;

}
