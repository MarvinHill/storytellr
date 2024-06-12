import {Component, OnInit} from '@angular/core';
import {Chapter} from "../../model/chapter";
import {ActivatedRoute, Router} from "@angular/router";
import {ChapterService} from "../../service/chapter.service";
import {ChapterMapperService} from "../../service/chapter-mapper.service";

@Component({
  selector: 'app-chapter-edit',
  templateUrl: './chapter-edit.component.html',
  styleUrl: './chapter-edit.component.scss'
})
export class ChapterEditComponent implements OnInit{
  chapterId!: string;
  chapter!: Chapter;
  saving: boolean = false;
  saved: boolean = true;
  savingError: boolean = false;


  constructor(private route: ActivatedRoute, private chapterService: ChapterService, private chapterMapperService: ChapterMapperService,
              private router: Router) {
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
    this.chapter.lastModified = new Date();
    const editChapter = this.chapterMapperService.mapChapterToEditChapterRequest(this.chapter);
    this.chapterService.updateChapter(editChapter).subscribe({
      next: (resp: Chapter) => {
        console.log(resp);
      },
      error: (error: any) => {
        console.error(error.message);
      }
    });
  }

  updateContent(event: any) {
    this.saved = false;
    this.saving = true;
    this.savingError = false;
    this.chapter.content = event;
    this.chapter.lastModified = new Date();
    const editChapter = this.chapterMapperService.mapChapterToEditChapterRequest(this.chapter);
    this.chapterService.updateChapter(editChapter).subscribe({
      next: (resp: Chapter) => {
        console.log(resp);
        setTimeout(() => {
          this.saving = false;
          this.saved = true;
        }, 1000);
      },
      error: (error: any) => {
        console.error(error.message);
        this.saving = false;
        this.saved = false;
        this.savingError = true;
      }
    });
  }

  publishChapter() {
    this.chapter.published = true;
    const editChapter = this.chapterMapperService.mapChapterToEditChapterRequest(this.chapter);
    this.chapterService.updateChapter(editChapter).subscribe({
      next: (resp: Chapter) => {
        console.log(resp);
      },
      error: (error: any) => {
        console.error(error.message);
      }
    });
  }

  unpublishChapter() {
    this.chapter.published = false;
    const editChapter = this.chapterMapperService.mapChapterToEditChapterRequest(this.chapter);
    this.chapterService.updateChapter(editChapter).subscribe({
      next: (resp: Chapter) => {
        console.log(resp);
      },
      error: (error: any) => {
        console.error(error.message);
      }
    });
  }

  closeEditor() {
    this.router.navigate(['/edit-details'], {queryParams: {bookId: this.chapter.bookId}});
  }
}
