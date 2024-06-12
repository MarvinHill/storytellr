import { CoverURI } from "./cover";

export interface Book {
  id: string;
  title: string;
  genreId: string;
  author: string;
  authorName: string
  description: string;
  catchphrase?: string;
  chapterIds: string[];
  tags: string[];
  cover?: CoverURI;
  public: boolean;
  adultContent: boolean;
  commentsDeactivated: boolean;
  finished: boolean;
}

export interface AddBookRequest {
  title: string;
  genreId: string;
  author: string;
  description: string;
  catchphrase?: string;
  chapterIds: string[];
  tags: string[];
  public: boolean;
  adultContent: boolean;
  commentsDeactivated: boolean;
  finished: boolean;
}

export interface EditBookRequest {
  id: string;
  title: string;
  genreId: string;
  author: string;
  description: string;
  catchphrase?: string;
  chapterIds: string[];
  tags: string[];
  public: boolean;
  adultContent: boolean;
  commentsDeactivated: boolean;
  finished: boolean;
}
