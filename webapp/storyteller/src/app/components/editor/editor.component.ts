import {Component, EventEmitter, Input, OnInit, Output, ViewEncapsulation} from '@angular/core';
import EditorJS from "@editorjs/editorjs";
import Header from '@editorjs/header';
import {ActivatedRoute} from "@angular/router";
import {ChapterService} from "../../service/chapter.service";
import {Chapter} from "../../model/chapter";
import {ChapterMapperService} from "../../service/chapter-mapper.service";


@Component({
  selector: 'app-editor',
  templateUrl: './editor.component.html',
  styleUrl: './editor.component.scss',
})
export class EditorComponent implements OnInit{
  editor: any;
  @Input() chapter!: Chapter;
  @Output() contentEmitter = new EventEmitter<string>();

  constructor(private route: ActivatedRoute, private chapterService: ChapterService, private chapterMapperService: ChapterMapperService) {
  }

  ngOnInit() {
    this.editor = new EditorJS({
      holder: 'editorjs',
      placeholder: 'Let`s write an awesome story!',
      tools: {
      header: Header
      }
    });
    this.editor.isReady.then(() => {
      const chapterContent = JSON.parse(this.chapter.content);
      this.editor.render(chapterContent);
    });
  }

  async saveContent() {

      const savedData = await this.editor.save();
      this.contentEmitter.emit(JSON.stringify(savedData));
  }
}