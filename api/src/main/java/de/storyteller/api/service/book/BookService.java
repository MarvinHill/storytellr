package de.storyteller.api.service.book;

import de.storyteller.api.dto.book.AddBookRequest;
import de.storyteller.api.dto.book.BookDTO;
import de.storyteller.api.dto.book.EditBookRequest;
import de.storyteller.api.dto.chapter.ChapterDTO;
import jakarta.transaction.Transactional;

import java.util.List;



public interface BookService {
    @Transactional
    List<BookDTO> getAllBooks();
    @Transactional
    BookDTO getBookById(Long id);
    @Transactional
    BookDTO createBook(AddBookRequest book);
    @Transactional
    BookDTO updateBook(EditBookRequest book);
    @Transactional
    List<ChapterDTO> getAllChapters(Long bookId);

}
