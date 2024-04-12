package de.storyteller.api.service.book;

import de.storyteller.api.v1.dto.book.AddBookRequest;
import de.storyteller.api.v1.dto.book.BookDTO;
import de.storyteller.api.v1.dto.book.EditBookRequest;
import de.storyteller.api.v1.dto.chapter.ChapterDTO;
import de.storyteller.api.v1.dto.cover.CoverUriDTO;
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

    void updateBookCover(UUID id, CoverUriDTO coverUriDTO);
}
