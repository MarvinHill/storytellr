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

/**
 * Component for editing a chapter
 */
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

  /**
   * Updates the title of the chapter
   */
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

  /**
   * Updates the content of the chapter
   * @param event the content of the chapter
   */
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

  /**
   * Publishes the chapter to be visible to the public
   */
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

  /**
   * Unpublishes the chapter to be hidden from the public
   */
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

  /**
   * Closes the editor and navigates back to the edit details page
   */
  closeEditor() {
    this.router.navigate(['/edit-details'], {queryParams: {bookId: this.chapter.bookId}});
  }
}
