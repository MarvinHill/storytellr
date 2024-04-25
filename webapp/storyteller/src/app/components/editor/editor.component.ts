import {Component, OnInit} from '@angular/core';
import EditorJS from "@editorjs/editorjs";
import Header from '@editorjs/header';


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
      placeholder: 'Let`s write an awesome story!',
      tools: {
      header: Header
      }
    });
  }

  async saveContent() {

      const savedData = await this.editor.save();
      console.log("Saved" + JSON.stringify(savedData));
  }

}
