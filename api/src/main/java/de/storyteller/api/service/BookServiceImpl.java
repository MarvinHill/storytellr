package de.storyteller.api.service;

import de.storyteller.api.dto.BookDTO;
import de.storyteller.api.dto.ChapterDTO;
import de.storyteller.api.mapper.BookMapper;
import de.storyteller.api.model.Book;
import de.storyteller.api.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(bookMapper::bookToBookDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO getBookById(Long id) {
        return null;
    }

    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        return null;
    }

    @Override
    public BookDTO updateBook(BookDTO bookDTO) {
        return null;
    }

    @Override
    public void deleteBook(Long id) {

    }

    @Override
    public List<ChapterDTO> getAllChapters(Long bookId) {
        return null;
    }
}
