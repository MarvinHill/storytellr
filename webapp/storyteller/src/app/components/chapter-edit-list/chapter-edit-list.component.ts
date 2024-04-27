import {Component, Input, OnInit} from '@angular/core';
import {Book} from "../../model/book";
import {AddChapterRequest, Chapter} from "../../model/chapter";
import {ChapterService} from "../../service/chapter.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-chapter-edit-list',
  templateUrl: './chapter-edit-list.component.html',
  styleUrl: './chapter-edit-list.component.scss'
})
export class ChapterEditListComponent implements OnInit{
  @Input() book!: Book;
  chapters: Chapter[] = [];

  constructor(private chapterService: ChapterService, private router: Router) {
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

  addChapter() {
    const chapter: AddChapterRequest = {
        chapterTitle: 'New Chapter',
        content: '',
        lastModified: new Date(),
        bookId: this.book.id
        };

    this.chapterService.addChapter(chapter).subscribe({
        next: (resp: Chapter) => {
            this.router.navigate(['/editor'], {queryParams: {chapterId: resp.id}});

        },
        error: (error: any) => {
            console.error(error.message);
        }
        });

    }
}
