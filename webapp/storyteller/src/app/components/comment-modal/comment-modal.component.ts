import {Component, Input, OnInit} from '@angular/core';
import {CommentService} from "../../service/comment.service";

@Component({
  selector: 'app-comment-modal',
  templateUrl: './comment-modal.component.html',
  styleUrl: './comment-modal.component.scss'
})
export class CommentModalComponent implements OnInit{
  @Input() blockId?: string;
  constructor(private commentService: CommentService) { }

  ngOnInit() {
    if (this.blockId) {
      this.getCommentsByBlockId(this.blockId);
    }
  }

  getCommentsByBlockId(blockId: string) {
    this.commentService.getCommentsByBlockId(blockId).subscribe(comments => {
      console.log(comments);
    });
  }

}
