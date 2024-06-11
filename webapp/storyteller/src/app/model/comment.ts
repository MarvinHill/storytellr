export interface Comment {
  chapterId: string;
  author: string;
  content: string;
  blockId: string;
  createdAt: Date;
}

export interface AddCommentRequest {
  content: string;
  chapterId: string;
  blockId: string;
}