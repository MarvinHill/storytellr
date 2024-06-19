import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {CommentService} from "../../service/comment.service";
import {AddCommentRequest, Comment} from "../../model/comment";
import {KeycloakService} from "keycloak-angular";

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

  constructor(private commentService: CommentService, private keycloakService: KeycloakService) {
  }

  ngOnInit() {
    if (this.blockId && this.chapterId) {
      this.getCommentsByChapterIdAndBlockId(this.chapterId, this.blockId);
    }
  }

  /**
   * Get comments by chapter id and block id
   * @param chapterId The id of the chapter which the comments belong to
   * @param blockId The id of the block which the comments belong to
   */
  getCommentsByChapterIdAndBlockId(chapterId: string, blockId: string) {
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
   * Check if the user is logged in
   */
  isUserLoggedIn(): boolean {
    return this.keycloakService.isLoggedIn();
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
            if (this.chapterId && this.blockId) {
              this.getCommentsByChapterIdAndBlockId(this.chapterId, this.blockId);
            }
          },
          error: (error: any) => {
            console.error(error.message);
          }
        }
      )
    }

  }

  /**
   * Emits the close event to close the comment modal
   */
  closeComments() {
    this.closeEvent.emit();
  }
}
