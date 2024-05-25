package de.storyteller.api.v1.controller;

import de.storyteller.api.model.Library;
import de.storyteller.api.service.library.LibraryService;
import de.storyteller.api.v1.dto.book.BookDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/library")
public class LibraryController {
    private final LibraryService libraryService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/all")
    public ResponseEntity<List<BookDTO>> getAllBooksFromLibrary(){
        return ResponseEntity.ok(libraryService.getAllBooksFromLibrary());
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/add/{bookId}")
    public ResponseEntity<Library> addBookToLibrary(@PathVariable String bookId){
        return ResponseEntity.ok(libraryService.addBookToLibrary(bookId));
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/remove/{bookId}")
    public ResponseEntity<Library> removeBookFromLibrary(@PathVariable String bookId){
        return ResponseEntity.ok(libraryService.removeBookFromLibrary(bookId));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/contains/{bookId}")
    public ResponseEntity<Boolean> containsBook(@PathVariable String bookId){
        return ResponseEntity.ok(libraryService.containsBook(bookId));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/random")
    public ResponseEntity<List<BookDTO>> getRandomBooks(){
        return ResponseEntity.ok(libraryService.getRandomBooks());
    }


}
