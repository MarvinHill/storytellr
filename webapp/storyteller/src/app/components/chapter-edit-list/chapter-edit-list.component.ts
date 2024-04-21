import {Component, Input, OnInit} from '@angular/core';
import {Book} from "../../model/book";
import {Chapter} from "../../model/chapter";
import {ChapterService} from "../../service/chapter.service";

@Component({
  selector: 'app-chapter-edit-list',
  templateUrl: './chapter-edit-list.component.html',
  styleUrl: './chapter-edit-list.component.scss'
})
export class ChapterEditListComponent implements OnInit{
  @Input() book!: Book;
  chapters: Chapter[] = [];

  constructor(private chapterService: ChapterService) {
  }

  ngOnInit() {
    this.getChapters();
  }

  getChapters() {
    for (let chapterId of this.book.chapterIds) {
      console.log(chapterId);
      this.chapterService.getChapterById(chapterId).subscribe((chapter: Chapter) => {
        this.chapters.push(chapter);
      });
    }
  }

}
