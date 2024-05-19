package de.storyteller.api.service.library;

import de.storyteller.api.v1.dto.book.BookDTO;

import java.util.List;

public interface LibraryService {
    List<BookDTO> getAllBooksFromLibrary();
    BookDTO addBookToLibrary(String bookId);
    BookDTO removeBookFromLibrary(String bookId);
}
