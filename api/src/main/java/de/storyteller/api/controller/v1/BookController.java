package de.storyteller.api.controller.v1;

import de.storyteller.api.dto.book.AddBookRequest;
import de.storyteller.api.dto.book.BookDTO;
import de.storyteller.api.dto.book.EditBookRequest;
import de.storyteller.api.service.book.BookService;
import jakarta.validation.Valid;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    private final BookService bookService;
    @PreAuthorize("permitAll()")
    @GetMapping("/all")
    public ResponseEntity<?> getAllBooks(){
        return ResponseEntity.ok(bookService.getAllBooks());
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/add")
    public ResponseEntity<BookDTO> addEvent(@Valid @RequestBody AddBookRequest book) {
        return new ResponseEntity<>(bookService.createBook(book), HttpStatus.CREATED);
    }
    @PreAuthorize("isAuthenticated() && @sAuthService.userOwnsBook(#book.id)")
    @PutMapping("/update")
    public ResponseEntity<BookDTO> updateEvent(@Valid @RequestBody EditBookRequest book) {
        return ResponseEntity.ok(bookService.updateBook(book));
    }
    @PreAuthorize("permitAll()")
    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable UUID id){
        Optional<BookDTO> bookDTO = bookService.getBookById(id);
        return bookDTO.isPresent() ? ResponseEntity.ok(bookDTO.get()) : ResponseEntity.notFound().build();
    }
    @PreAuthorize("permitAll()")
    @GetMapping("/{id}/chapters")
    public ResponseEntity<?> getBookChapters(@PathVariable UUID id){
        return ResponseEntity.ok(bookService.getAllChapters(id));
    }
}
