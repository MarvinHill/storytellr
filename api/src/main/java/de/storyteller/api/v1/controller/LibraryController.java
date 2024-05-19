package de.storyteller.api.v1.controller;

import de.storyteller.api.service.library.LibraryService;
import de.storyteller.api.v1.dto.book.BookDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/library")
public class LibraryController {
    private final LibraryService libraryService;

    @GetMapping("/all")
    public ResponseEntity<List<BookDTO>> getAllBooksFromLibrary(){
        return ResponseEntity.ok(libraryService.getAllBooksFromLibrary());
    }

    @PutMapping("/add/{bookId}")
    public ResponseEntity<BookDTO> addBookToLibrary(@PathVariable String bookId){
        return ResponseEntity.ok(libraryService.addBookToLibrary(bookId));
    }

    @PutMapping("/remove/{bookId}")
    public ResponseEntity<BookDTO> removeBookFromLibrary(@PathVariable String bookId){
        return ResponseEntity.ok(libraryService.removeBookFromLibrary(bookId));
    }

    @GetMapping("/contains/{bookId}")
    public ResponseEntity<Boolean> containsBook(@PathVariable String bookId){
        return ResponseEntity.ok(libraryService.containsBook(bookId));
    }

}
