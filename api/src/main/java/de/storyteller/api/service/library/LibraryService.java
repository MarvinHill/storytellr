package de.storyteller.api.service.library;

import de.storyteller.api.model.Library;
import de.storyteller.api.v1.dto.book.BookDTO;

import java.util.List;

public interface LibraryService {
    List<BookDTO> getAllBooksFromLibrary();
    Library addBookToLibrary(String bookId);
    Library removeBookFromLibrary(String bookId);

    boolean containsBook(String bookId);
}
