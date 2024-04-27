import {Component, OnInit} from '@angular/core';
import EditorJS from "@editorjs/editorjs";
import Header from '@editorjs/header';
import {ActivatedRoute} from "@angular/router";
import {ChapterService} from "../../service/chapter.service";
import {Chapter} from "../../model/chapter";


@Component({
  selector: 'app-editor',
  templateUrl: './editor.component.html',
  styleUrl: './editor.component.scss'
})
export class EditorComponent implements OnInit{
  editor: any;
  chapterId!: string;
  chapter!: Chapter;

  constructor(private route: ActivatedRoute, private chapterService: ChapterService) {
  }

  ngOnInit() {
    this.editor = new EditorJS({
      holder: 'editorjs',
      placeholder: 'Let`s write an awesome story!',
      tools: {
      header: Header
      }
    });
    this.route.queryParams.subscribe(params => {
        this.chapterId = params['chapterId'];
        this.chapterService.getChapterById(this.chapterId).subscribe((chapter: Chapter) => {
          this.chapter = chapter;
        });
    });
  }

  async saveContent() {

      const savedData = await this.editor.save();
      console.log("Saved" + JSON.stringify(savedData));
  }

}
