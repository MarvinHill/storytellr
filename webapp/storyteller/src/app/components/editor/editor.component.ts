import {Component, OnInit} from '@angular/core';
import EditorJS from "@editorjs/editorjs";
import Header from '@editorjs/header';
import {ActivatedRoute} from "@angular/router";
import {ChapterService} from "../../service/chapter.service";
import {Chapter} from "../../model/chapter";
import {ChapterMapperService} from "../../service/chapter-mapper.service";


@Component({
  selector: 'app-editor',
  templateUrl: './editor.component.html',
  styleUrl: './editor.component.scss'
})
export class EditorComponent implements OnInit{
  editor: any;
  chapterId!: string;
  chapter!: Chapter;

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

  updateChapterTitle() {
    const editChapter = this.chapterMapperService.mapChapterToEditChapterRequest(this.chapter);
    this.chapterService.updateChapter(editChapter).subscribe({
      next: (resp: Chapter) => {
        console.log("Chapter updated");
      },
      error: (error: any) => {
        console.error(error.message);
      }
    });
  }
}
