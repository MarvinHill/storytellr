import { Component } from '@angular/core';
import {Chapter} from "../../model/chapter";
import {ActivatedRoute} from "@angular/router";
import {ChapterService} from "../../service/chapter.service";
import {ChapterMapperService} from "../../service/chapter-mapper.service";
import EditorJS from "@editorjs/editorjs";
import Header from "@editorjs/header";

@Component({
  selector: 'app-chapter-edit',
  templateUrl: './chapter-edit.component.html',
  styleUrl: './chapter-edit.component.scss'
})
export class ChapterEditComponent {
  chapterId!: string;
  chapter!: Chapter;

  constructor(private route: ActivatedRoute, private chapterService: ChapterService, private chapterMapperService: ChapterMapperService) {
  }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.chapterId = params['chapterId'];
      this.chapterService.getChapterById(this.chapterId).subscribe((chapter: Chapter) => {
        this.chapter = chapter;
      });
    });
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
