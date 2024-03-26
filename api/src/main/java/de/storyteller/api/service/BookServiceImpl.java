package de.storyteller.api.service;

import de.storyteller.api.dto.book.AddBookRequest;
import de.storyteller.api.dto.book.BookDTO;
import de.storyteller.api.dto.book.EditBookRequest;
import de.storyteller.api.dto.chapter.ChapterDTO;
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
                .map(bookMapper::toBookDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO getBookById(Long id) {
        return null;
    }

    @Override
    public BookDTO createBook(AddBookRequest book) {
        return bookMapper.toBookDTO(bookRepository.save(bookMapper.toBook(book)));
    }

    @Override
    public BookDTO updateBook(EditBookRequest book)  {
        if (!bookRepository.existsById(book.getId())) {
            throw new RuntimeException("Event with id: " + book.getId() + " doesn't exist");
        }
        return bookMapper.toBookDTO(bookRepository.save(bookMapper.toBook(book)));
    }

    @Override
    public void deleteBook(Long id) {

    }

    @Override
    public List<ChapterDTO> getAllChapters(Long bookId) {
        return null;
    }
}
