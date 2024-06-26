package de.storyteller.api.v1.controller;

import de.storyteller.api.model.Library;
import de.storyteller.api.service.library.LibraryService;
import de.storyteller.api.v1.dto.book.BookDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling library related requests
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/library")
public class LibraryController {
    private final LibraryService libraryService;

    /**
     * Get all books from the library for the logged in user
     * @return List of all books from the library
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/all")
    public ResponseEntity<List<BookDTO>> getAllBooksFromLibrary(){
        return ResponseEntity.ok(libraryService.getAllBooksFromLibrary());
    }

    /**
     * Add a book to the library
     * @param bookId Id of the book to be added
     * @return Library with the book added
     */
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/add/{bookId}")
    public ResponseEntity<Library> addBookToLibrary(@PathVariable String bookId){
        return ResponseEntity.ok(libraryService.addBookToLibrary(bookId));
    }

    /**
     * Remove a book from the library
     * @param bookId Id of the book to be removed
     * @return Library with the book removed
     */
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/remove/{bookId}")
    public ResponseEntity<Library> removeBookFromLibrary(@PathVariable String bookId){
        return ResponseEntity.ok(libraryService.removeBookFromLibrary(bookId));
    }

    /**
     * Check if the library contains a book
     * @param bookId Id of the book
     * @return True if the library contains the book, false otherwise
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/contains/{bookId}")
    public ResponseEntity<Boolean> containsBook(@PathVariable String bookId){
        return ResponseEntity.ok(libraryService.containsBook(bookId));
    }

    /**
     * Get random books from the library
     * @return List of random books from the library
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/random")
    public ResponseEntity<List<BookDTO>> getRandomBooks(){
        return ResponseEntity.ok(libraryService.getRandomBooks());
    }

    /**
     * Like a book
     * @param bookId Id of the book to be liked
     * @return Library with the book liked
     */
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/like/{bookId}")
    public ResponseEntity<Library> likeBook(@PathVariable String bookId){
        return ResponseEntity.ok(libraryService.likeBook(bookId));
    }

    /**
     * Unlike a book
     * @param bookId Id of the book to be unliked
     * @return Library with the book unliked
     */
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/unlike/{bookId}")
    public ResponseEntity<Library> unlikeBook(@PathVariable String bookId){
        return ResponseEntity.ok(libraryService.unlikeBook(bookId));
    }

    /**
     * Check if a book is liked
     * @param bookId Id of the book
     * @return True if the book is liked, false otherwise
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/isLiked/{bookId}")
    public ResponseEntity<Boolean> isBookLiked(@PathVariable String bookId){
        return ResponseEntity.ok(libraryService.isBookLiked(bookId));
    }


}
