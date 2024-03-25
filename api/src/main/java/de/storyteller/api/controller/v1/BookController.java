package de.storyteller.api.controller.v1;

import de.storyteller.api.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    private final BookService bookService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllBooks(){
        return ResponseEntity.ok(bookService.getAllBooks());
    }
}
