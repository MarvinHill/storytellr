interface Book {
  id: string;
  title: string;
  genreId: string;
  author: string;
  description: string;
  catchphrase?: string;
  cover?: string;
}

interface AddBookRequest {
  title: string;
  genreId: string;
  author: string;
  description: string;
  catchphrase?: string;
  cover?: string;
}

interface EditBookRequest {
  id: string;
  title: string;
  genreId: string;
  author: string;
  description: string;
  catchphrase?: string;
  cover?: string;
}
