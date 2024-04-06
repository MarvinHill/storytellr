package de.storyteller.api.service.book;

import de.storyteller.api.dto.book.AddBookRequest;
import de.storyteller.api.dto.book.BookDTO;
import de.storyteller.api.dto.book.EditBookRequest;
import de.storyteller.api.dto.chapter.ChapterDTO;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface BookService {
    @Transactional
    List<BookDTO> getAllBooks();
    @Transactional
    Optional<BookDTO> getBookById(UUID id);
    @Transactional
    BookDTO createBook(AddBookRequest book);
    @Transactional
    BookDTO updateBook(EditBookRequest book);
    @Transactional
    List<ChapterDTO> getAllChapters(UUID bookId);

}
