package de.storyteller.api.service.library;

import de.storyteller.api.model.Library;
import de.storyteller.api.repository.LibraryRepository;
import de.storyteller.api.service.UserService;
import de.storyteller.api.service.book.BookService;
import de.storyteller.api.v1.dto.book.BookDTO;
import de.storyteller.api.v1.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class LibraryServiceImpl implements LibraryService {
    private final UserService userService;
    private final LibraryRepository libraryRepository;
    private final BookMapper bookMapper;
    private final BookService bookService;

    @Override
    public List<BookDTO> getAllBooksFromLibrary() {
        String userId = userService.getUserId();
        Optional<Library> libraryOptional = libraryRepository.findByUserId(userId);
        if (libraryOptional.isPresent()) {
            return libraryOptional.get().getBooks().stream().map(bookMapper::toBookDTO).toList();
        } else {
            Library library = new Library();
            library.setUserId(userId);
            library.setBooks(List.of());
            libraryRepository.save(library);
            return library.getBooks().stream().map(bookMapper::toBookDTO).toList();
        }
    }

    @Override
    public Library addBookToLibrary(String bookId) {
        String userId = userService.getUserId();
        Optional<Library> libraryOptional = libraryRepository.findByUserId(userId);
        if (libraryOptional.isPresent()) {
            Library library = libraryOptional.get();
            Optional<BookDTO> bookDTO = bookService.getBookById(bookId);
            library.getBooks().add(bookMapper.toBook(bookDTO.get()));
            libraryRepository.save(library);
            return library;
        }
        else {
            Library library = new Library();
            library.setUserId(userId);
            BookDTO book = bookService.getBookById(bookId).get();
            library.getBooks().add(bookMapper.toBook(book));
            libraryRepository.save(library);
            return library;
        }
    }

    @Override
    public Library removeBookFromLibrary(String bookId) {
        String userId = userService.getUserId();
        System.out.println(libraryRepository.findByUserId(userId));
        Optional<Library> libraryOptional = libraryRepository.findByUserId(userId);
        if (libraryOptional.isPresent()) {
            Library library = libraryOptional.get();
            library.getBooks().removeIf(book -> book.getId().equals(bookId));
            libraryRepository.save(library);
            return library;
        }
        throw new RuntimeException("Library not found");
    }

    @Override
    public boolean containsBook(String bookId) {
        String userId = userService.getUserId();
        Optional<Library> libraryOptional = libraryRepository.findByUserId(userId);
        if (libraryOptional.isPresent()) {
            Library library = libraryOptional.get();
            return library.getBooks().stream().anyMatch(book -> book.getId().equals(bookId));
        }
        return false;
    }

    @Override
    public List<BookDTO> getRandomBooks() {
        String userId = userService.getUserId();
        Optional<Library> libraryOptional = libraryRepository.findByUserId(userId);
        if (libraryOptional.isPresent()) {
            Library library = libraryOptional.get();
            List<BookDTO> books = new java.util.ArrayList<>(library.getBooks().stream().map(bookMapper::toBookDTO).toList());
            Collections.shuffle(books);
            return books;

        }
        throw new RuntimeException("Library not found");
    }
}
