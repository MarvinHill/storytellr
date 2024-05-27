package de.storyteller.api.service.book;

import de.storyteller.api.model.Progress;
import de.storyteller.api.repository.ProgressRepository;
import de.storyteller.api.service.UserService;
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
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final ChapterRepository chapterRepository;
    private final ChapterMapper chapterMapper;
    private final UserService userService;
    private final ProgressRepository progressRepository;

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
        Book bookEntity = bookMapper.toBook(book);
        bookEntity.setCover(
            new CoverUriDTO(
                "assets/images/cover-original.png",
                "assets/images/cover-sm.png",
                "assets/images/cover-lg.png")
            );
        bookEntity.setAuthor(userService.getUserId());
        BookDTO dto = bookMapper.toBookDTO(bookRepository.save(bookEntity));
        log.info("Create book with id: {}", dto.getId());
        return dto;
    }

    @Override
    public BookDTO updateBook(EditBookRequest book)  {
        Optional<Book> currentBook = bookRepository.findById(book.getId());
        if (currentBook.isEmpty()) {
            throw new RuntimeException("Book with id: " + book.getId() + " doesn't exist");
        }
        log.info("Update book with id: {}", book.getId());
        Book updatedBook = bookMapper.toBook(book);
        updatedBook.setCover(currentBook.get().getCover());
        updatedBook.setAuthor(currentBook.get().getAuthor());
        return bookMapper.toBookDTO( bookRepository.save(updatedBook));
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

    @Override
    public List<BookDTO> getBooksByAuthor() {
        String userId = userService.getUserId();
        List<Book> books = bookRepository.findByAuthor(userId);
        return books.stream()
                .map(bookMapper::toBookDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO getBookWithPublishedChapters(String bookId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if(bookOptional.isPresent()) {
            Book book = bookOptional.get();
            List<Chapter> chapters = book.getChapters();
            List<Chapter> publishedChapters = chapters.stream()
                    .filter(Chapter::isPublished)
                    .collect(Collectors.toList());
            book.setChapters(publishedChapters);
            return bookMapper.toBookDTO(book);
        }
        return null;
    }

    @Override
    public int getBookProgress(String bookId) {
        Optional<Progress> progress = this.progressRepository.findByBookIdAndUserId(bookId, userService.getUserId());
        return progress.map(Progress::getReadChapters).orElse(0);
    }

    @Override
    public void increaseProgress(String bookId) {
        Optional<Progress> progressOptional = this.progressRepository.findByBookIdAndUserId(bookId, userService.getUserId());
        if(progressOptional.isPresent()) {
            Progress progress = progressOptional.get();
            int maxChapters = getBookWithPublishedChapters(bookId).getChapterIds().size();
            if (progress.getReadChapters() >= maxChapters) {
                return;
            }
            progress.setReadChapters(progress.getReadChapters() + 1);
            this.progressRepository.save(progress);
        } else {
            Progress progress = new Progress();
            progress.setBookId(bookId);
            progress.setUserId(userService.getUserId());
            progress.setReadChapters(1);
            this.progressRepository.save(progress);
        }
    }
}
