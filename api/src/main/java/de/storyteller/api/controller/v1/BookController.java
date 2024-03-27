package de.storyteller.api.controller.v1;

import de.storyteller.api.dto.book.AddBookRequest;
import de.storyteller.api.dto.book.BookDTO;
import de.storyteller.api.dto.book.EditBookRequest;
import de.storyteller.api.service.book.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    private final BookService bookService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllBooks(){
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @PostMapping("/add")
    public ResponseEntity<BookDTO> addEvent(@Valid @RequestBody AddBookRequest book) {
        return new ResponseEntity<>(bookService.createBook(book), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<BookDTO> updateEvent(@Valid @RequestBody EditBookRequest book) {
        System.out.println("Book: " + book);
        return ResponseEntity.ok(bookService.updateBook(book));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable UUID id){
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @GetMapping("/{id}/chapters")
    public ResponseEntity<?> getBookChapters(@PathVariable UUID id){
        return ResponseEntity.ok(bookService.getAllChapters(id));
    }
}
