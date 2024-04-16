export interface Book {
  id: string;
  title: string;
  genreId: string;
  author: string;
  description: string;
  catchphrase?: string;
  chapterIds: string[];
  tags: string[];
  cover?: string;
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
  cover?: string;
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
  cover?: string;
  public: boolean;
  adultContent: boolean;
  commentsDeactivated: boolean;
  finished: boolean;
}
