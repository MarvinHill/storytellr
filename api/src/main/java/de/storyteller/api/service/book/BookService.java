package de.storyteller.api.service.book;

import de.storyteller.api.v1.dto.book.AddBookRequest;
import de.storyteller.api.v1.dto.book.BookDTO;
import de.storyteller.api.v1.dto.book.EditBookRequest;
import de.storyteller.api.v1.dto.chapter.ChapterDTO;
import de.storyteller.api.v1.dto.cover.CoverUriDTO;

import java.util.List;
import java.util.Optional;

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

    /**
     * Update the cover of a book
     * @param id the id of the book
     * @param coverUriDTO the new cover uri
     */
    void updateBookCover(String id, CoverUriDTO coverUriDTO);

    /**
     * Get all books of an author
     * @return a list of all books of an author
     */
    List<BookDTO> getBooksByAuthor();

    /**
     * Get a book with only the published chapters
     * @param bookId the id of the book
     * @return the book with only the published chapters
     */
    BookDTO getBookWithPublishedChapters(String bookId);

    /**
     * Increase the likes of a book
     * @param bookId the id of the book
     */
    void increaseBookLikes(String bookId);

    /**
     * Decrease the likes of a book
     * @param bookId the id of the book
     */
    void decreaseBookLikes(String bookId);

    /**
     * Get the progress of a book
     * @param bookId the id of the book to get the progress of
     * @return the progress of the book
     */
    int getBookProgress(String bookId);

    /**
     * Increase the progress of a book
     * @param bookId the id of the book
     * @param progress the progress to increase
     */
    void increaseProgress(String bookId, int progress);
}
