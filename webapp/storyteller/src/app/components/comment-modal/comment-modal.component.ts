import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {CommentService} from "../../service/comment.service";
import {AddCommentRequest, Comment} from "../../model/comment";

@Component({
  selector: 'app-comment-modal',
  templateUrl: './comment-modal.component.html',
  styleUrl: './comment-modal.component.scss'
})
export class CommentModalComponent implements OnInit {
  @Input() blockId?: string;
  @Input() chapterId?: string;
  commentContent: string = '';
  comments: Comment[] = [];
  @Output() addCommentEvent: EventEmitter<AddCommentRequest> = new EventEmitter<AddCommentRequest>();

  constructor(private commentService: CommentService) {
  }

  ngOnInit() {
    if (this.blockId) {
      this.getCommentsByBlockId(this.blockId);
    }
  }

  getCommentsByBlockId(blockId: string) {
    this.commentService.getCommentsByBlockId(blockId).subscribe({
      next: (comments) => {
        this.comments = comments;
      },
      error: (error) => {
        console.error(error);
      }
    });
    }


  addComment() {
    if (this.blockId && this.chapterId) {
      let comment: AddCommentRequest = {
        content: this.commentContent,
        chapterId: this.chapterId,
        blockId: this.blockId
      };
      this.addCommentEvent.emit(comment);
    }

  }
}
