package de.storyteller.api.v1.controller;

import de.storyteller.api.v1.dto.book.AddBookRequest;
import de.storyteller.api.v1.dto.book.BookDTO;
import de.storyteller.api.v1.dto.book.EditBookRequest;
import de.storyteller.api.service.book.BookService;
import de.storyteller.api.v1.dto.chapter.ChapterDTO;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling book related requests
 * @version 1.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    private final BookService bookService;

    /**
     * Get all books
     * @return List of all books
     */
    @PreAuthorize("permitAll()")
    @GetMapping("/all")
    public ResponseEntity<List<BookDTO>> getAllBooks(){
        return ResponseEntity.ok(bookService.getAllBooks());
    }
    /**
     * Add a new book if the user is authenticated
     * @param book Book to be added
     * @return Book added
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/add")
    public ResponseEntity<BookDTO> addBook(@Valid @RequestBody AddBookRequest book) {
        return new ResponseEntity<>(bookService.createBook(book), HttpStatus.CREATED);
    }
    /**
     * Update a book if the user is authenticated and owns the book
     * @param book Book to be updated
     * @return Book updated
     */
    @PreAuthorize("isAuthenticated() && (@sAuthService.userOwnsBook(#book.id) || @sAuthService.isAdmin())")
    @PutMapping("/update")
    public ResponseEntity<BookDTO> updateBook(@Valid @RequestBody EditBookRequest book) {
        return ResponseEntity.ok(bookService.updateBook(book));
    }

    /**
     * Get a book by its id
     * @param id Id of the book
     * @return Book with the given id
     */
    @PreAuthorize("permitAll()")
    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable String id){
        Optional<BookDTO> bookDTO = bookService.getBookById(id);
        return bookDTO.isPresent() ? ResponseEntity.ok(bookDTO.get()) : ResponseEntity.notFound().build();
    }


    /**
     * Get a book with all published chapters
     * @param id Id of the book
     * @return Book with all published chapters
     */
    @PreAuthorize("permitAll()")
    @GetMapping("/{id}/published")
    public ResponseEntity<BookDTO> getBookWithPublishedChapters(@PathVariable String id){
        return ResponseEntity.ok(bookService.getBookWithPublishedChapters(id));
    }

    /**
     * Get all chapters of a book
     * @param id Id of the book
     * @return List of chapters of the book
     */
    @PreAuthorize("permitAll()")
    @GetMapping("/{id}/chapters")
    public ResponseEntity<List<ChapterDTO>> getBookChapters(@PathVariable String id){
        return ResponseEntity.ok(bookService.getAllChapters(id));
    }

    /**
     * Get all books of the logged in user
     * @return List of books of the logged in user
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/user")
    public ResponseEntity<List<BookDTO>> getBooksByAuthor(){
        return ResponseEntity.ok(bookService.getBooksByAuthor());
    }

    /**
     * Increase the progress of a book for the logged in user
     * @param id Id of the book
     * @param progress Progress to be added
     * @return Ok if the progress was increased
     */
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/progress/increase/{id}/{progress}")
    public ResponseEntity<?> increaseProgress(@PathVariable String id, @PathVariable int progress){
        bookService.increaseProgress(id, progress);
        return ResponseEntity.ok().build();
    }

    /**
     * Get the progress of a book for the logged in user
     * @param id Id of the book
     * @return Progress of the book
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/progress/{id}")
    public ResponseEntity<Integer> getBookProgress(@PathVariable String id){
        return ResponseEntity.ok(bookService.getBookProgress(id));
    }




}
