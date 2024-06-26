/**
 * Comment model class
 */

export interface Comment {
  chapterId: string;
  authorId: string;
  authorName: string;
  content: string;
  blockId: string;
  createdAt: Date;
}

export interface AddCommentRequest {
  content: string;
  chapterId: string;
  blockId: string;
}