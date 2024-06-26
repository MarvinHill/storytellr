package de.storyteller.api.service.library;

import de.storyteller.api.model.Library;
import de.storyteller.api.v1.dto.book.BookDTO;

import java.util.List;

/**
 * Service for managing the library of a user.
 */
public interface LibraryService {
    /**
     * Get all books from the library of the logged in user.
     * @return List of books in the library.
     */
    List<BookDTO> getAllBooksFromLibrary();

    /**
     * Add a book to the library of the logged in user.
     * @param bookId Id of the book to add.
     * @return Updated library.
     */
    Library addBookToLibrary(String bookId);

    /**
     * Remove a book from the library of the logged in user.
     * @param bookId Id of the book to remove.
     * @return Updated library.
     */
    Library removeBookFromLibrary(String bookId);

    /**
     * Check if a book is in the library of the logged in user.
     * @param bookId Id of the book to check.
     * @return True if the book is in the library, false otherwise.
     */
    boolean containsBook(String bookId);

    /**
     * Get a list of random books from the library of the logged in user.
     * @return List of random books.
     */
    List<BookDTO> getRandomBooks();

    /**
     * Like a book
     * @param bookId Id of the book to like
     * @return Updated library
     */
    Library likeBook(String bookId);

    /**
     * Unlike a book
     * @param bookId Id of the book to unlike
     * @return Updated library
     */
    Library unlikeBook(String bookId);

    /**
     * Check if a book is liked
     * @param bookId Id of the book to check
     * @return True if the book is liked, false otherwise
     */
    boolean isBookLiked(String bookId);
}
