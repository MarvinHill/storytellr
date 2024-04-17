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

/**
 * Service for books
 */
public interface BookService {

    /**
     * Get all books
     * @return a list of all books
     */
    List<BookDTO> getAllBooks();

    /**
     * Get a book by its id
     * @param id the id of the book
     * @return the book with the given id
     */
    Optional<BookDTO> getBookById(String id);

    /**
     * Create a book
     * @param book the book to create
     * @return the created book
     */
    BookDTO createBook(AddBookRequest book);

    /**
     * Update a book
     * @param book the book to update
     * @return the updated book
     */
    BookDTO updateBook(EditBookRequest book);

    /**
     * Get all chapters of a book
     * @param bookId the id of the book
     * @return a list of all chapters of the book
     */
    List<ChapterDTO> getAllChapters(String bookId);

    void updateBookCover(UUID id, CoverUriDTO coverUriDTO);
}
