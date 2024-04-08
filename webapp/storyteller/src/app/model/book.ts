export interface Book {
  id: string;
  title: string;
  genreId: string;
  author: string;
  description: string;
  catchphrase?: string;
  tags: string[];
  cover?: string;
}

export interface AddBookRequest {
  title: string;
  genreId: string;
  author: string;
  description: string;
  catchphrase?: string;
  tags: string[];
  cover?: string;
}

export interface EditBookRequest {
  id: string;
  title: string;
  genreId: string;
  author: string;
  description: string;
  catchphrase?: string;
  tags: string[];
  cover?: string;
}
