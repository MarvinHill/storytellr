package de.storyteller.api.service;

import de.storyteller.api.dto.book.AddBookRequest;
import de.storyteller.api.dto.book.BookDTO;
import de.storyteller.api.dto.book.EditBookRequest;
import de.storyteller.api.dto.chapter.ChapterDTO;

import java.util.List;

public interface BookService {
    List<BookDTO> getAllBooks();
    BookDTO getBookById(Long id);
    BookDTO createBook(AddBookRequest book);
    BookDTO updateBook(EditBookRequest book);
    void deleteBook(Long id);
    List<ChapterDTO> getAllChapters(Long bookId);

}
