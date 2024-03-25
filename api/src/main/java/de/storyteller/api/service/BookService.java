package de.storyteller.api.service;

import de.storyteller.api.dto.BookDTO;
import de.storyteller.api.dto.ChapterDTO;

import java.util.List;

public interface BookService {
    List<BookDTO> getAllBooks();
    BookDTO getBookById(Long id);
    BookDTO createBook(BookDTO bookDTO);
    BookDTO updateBook(BookDTO bookDTO);
    void deleteBook(Long id);
    List<ChapterDTO> getAllChapters(Long bookId);

}
