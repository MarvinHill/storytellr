import {Component, Input, OnInit} from '@angular/core';
import {Book} from "../../model/book";
import {AddChapterRequest, Chapter} from "../../model/chapter";
import {ChapterService} from "../../service/chapter.service";
import {Router} from "@angular/router";
import {firstValueFrom} from "rxjs";

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
    async getChapters() {
        for (let chapterId of this.book.chapterIds) {
            console.log(chapterId);
            const chapter = await firstValueFrom(this.chapterService.getChapterById(chapterId));
            this.chapters.push(chapter);
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

    updateChapterContent(chapter: Chapter) {
      this.router.navigate(['/editor'], {queryParams: {chapterId: chapter.id}});
    }
}
