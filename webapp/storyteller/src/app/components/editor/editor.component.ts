import {Component, OnInit} from '@angular/core';
import EditorJS from "@editorjs/editorjs";

@Component({
  selector: 'app-editor',
  templateUrl: './editor.component.html',
  styleUrl: './editor.component.scss'
})
export class EditorComponent implements OnInit{
  editor: any;

  ngOnInit() {
    this.editor = new EditorJS({
      holder: 'editorjs',
    });
  }

}
