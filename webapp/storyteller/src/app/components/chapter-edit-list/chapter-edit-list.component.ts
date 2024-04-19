import {Component, Input, OnInit} from '@angular/core';
import {Book} from "../../model/book";
import {Chapter} from "../../model/chapter";

@Component({
  selector: 'app-chapter-edit-list',
  templateUrl: './chapter-edit-list.component.html',
  styleUrl: './chapter-edit-list.component.scss'
})
export class ChapterEditListComponent implements OnInit{
  @Input() book!: Book;
  chapters: Chapter[] = [];

  constructor() {
  }

  ngOnInit() {
    // Get the chapters of the book from chapter service

    // Save them to chapters array
  }

  getChapters() {

  }

}
