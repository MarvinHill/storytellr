package de.storyteller.api.service.book;

import de.storyteller.api.v1.dto.book.AddBookRequest;
import de.storyteller.api.v1.dto.book.BookDTO;
import de.storyteller.api.v1.dto.book.EditBookRequest;
import de.storyteller.api.v1.dto.chapter.ChapterDTO;
import de.storyteller.api.v1.dto.cover.CoverUriDTO;
import de.storyteller.api.v1.mapper.BookMapper;
import de.storyteller.api.v1.mapper.ChapterMapper;
import de.storyteller.api.model.Book;
import de.storyteller.api.model.Chapter;
import de.storyteller.api.repository.BookRepository;
import de.storyteller.api.repository.ChapterRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Slf4j
@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final ChapterRepository chapterRepository;
    private final ChapterMapper chapterMapper;

    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(bookMapper::toBookDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BookDTO> getBookById(String id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        return bookOptional.isPresent() ? Optional.of(bookMapper.toBookDTO(bookOptional.get())) : Optional.empty();

    }

    @Override
    public BookDTO createBook(AddBookRequest book) {
        BookDTO dto = bookMapper.toBookDTO(bookRepository.save(bookMapper.toBook(book)));
        log.info("Create book with id: {}", dto.getId());
        return dto;
    }

    @Override
    public BookDTO updateBook(EditBookRequest book)  {
        if (!bookRepository.existsById(book.getId())) {
            throw new RuntimeException("Book with id: " + book.getId() + " doesn't exist");
        }
        log.info("Update book with id: {}", book.getId());
        return bookMapper.toBookDTO(bookRepository.save(bookMapper.toBook(book)));
    }

    @Override
    public List<ChapterDTO> getAllChapters(String bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        Book book1 = book.get();
        List<Chapter> chapterIds = book1.getChapters();
        return chapterIds.stream()
                .map(chapterMapper::toChapterDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void updateBookCover(String bookId, CoverUriDTO coverUriDTO) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if(bookOptional.isPresent()) {
            Book book = bookOptional.get();
            book.setCover(coverUriDTO);
            bookRepository.save(book);
        }
    }

}
