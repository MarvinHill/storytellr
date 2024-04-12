package de.storyteller.api.service.book;

import de.storyteller.api.dto.book.AddBookRequest;
import de.storyteller.api.dto.book.BookDTO;
import de.storyteller.api.dto.book.EditBookRequest;
import de.storyteller.api.dto.chapter.ChapterDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface BookService {

    List<BookDTO> getAllBooks();

    Optional<BookDTO> getBookById(String id);

    BookDTO createBook(AddBookRequest book);

    BookDTO updateBook(EditBookRequest book);

    List<ChapterDTO> getAllChapters(String bookId);

}
