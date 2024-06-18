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
  @Output() closeEvent: EventEmitter<void> = new EventEmitter<void>();

  constructor(private commentService: CommentService) {
  }

  ngOnInit() {
    if (this.blockId && this.chapterId) {
      this.getCommentsByBlockId(this.chapterId, this.blockId);
    }
  }

  getCommentsByBlockId(chapterId:string, blockId: string) {
    this.commentService.getCommentsByBlockId(chapterId, blockId).subscribe({
      next: (comments) => {
        this.comments = comments;
        console.log(this.comments);
      },
      error: (error) => {
        console.error(error);
      }
    });
    }


  /**
   * Add a comment to a chapter
   * @param comment the comment to be added
   */
  addComment() {
    if (this.blockId && this.chapterId) {
      let comment: AddCommentRequest = {
        content: this.commentContent,
        chapterId: this.chapterId,
        blockId: this.blockId
      };
      this.commentService.addComment(comment).subscribe(
        {
          next: (comment) => {
            console.log(comment);
            this.commentContent = "";
            if(this.chapterId && this.blockId){
              this.getCommentsByBlockId(this.chapterId, this.blockId);
            }
          },
          error: (error: any) => {
            console.error(error.message);
          }
        }
      )
    }

  }

  closeComments() {
    this.closeEvent.emit();
  }
}
