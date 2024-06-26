package de.storyteller.api.service.book;

import de.storyteller.api.model.Progress;
import de.storyteller.api.repository.ProgressRepository;
import de.storyteller.api.v1.auth.UserService;
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

/**
 * Implementation of the BookService
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final ChapterMapper chapterMapper;
    private final UserService userService;
    private final ProgressRepository progressRepository;

    /**
     * Get all books
     * @return a list of all books
     */
    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(bookMapper::toBookDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get a book by its id
     * @param id the id of the book
     * @return the book with the given id
     */
    @Override
    public Optional<BookDTO> getBookById(String id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        return bookOptional.isPresent() ? Optional.of(bookMapper.toBookDTO(bookOptional.get())) : Optional.empty();

    }

    /**
     * Create a book
     * @param book the book to create
     * @return the created book
     */
    @Override
    public BookDTO createBook(AddBookRequest book) {
        Book bookEntity = bookMapper.toBook(book);
        BookDTO dto = bookMapper.toBookDTO(bookRepository.save(bookEntity));
        log.info("Create book with id: {}", dto.getId());
        return dto;
    }

    /**
     * Update a book
     * @param book the book to update
     * @return the updated book
     */
    @Override
    public BookDTO updateBook(EditBookRequest book)  {
        Optional<Book> currentBook = bookRepository.findById(book.getId());
        if (currentBook.isEmpty()) {
            throw new RuntimeException("Book with id: " + book.getId() + " doesn't exist");
        }
        log.info("Update book with id: {}", book.getId());
        Book updatedBook = bookMapper.toBook(book);
        return bookMapper.toBookDTO( bookRepository.save(updatedBook));
    }

    /**
     * Get all chapters of a book
     * @param bookId the id of the book
     * @return a list of all chapters of the book
     */
    @Override
    public List<ChapterDTO> getAllChapters(String bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        Book book1 = book.get();
        List<Chapter> chapterIds = book1.getChapters();
        return chapterIds.stream()
                .map(chapterMapper::toChapterDTO)
                .collect(Collectors.toList());
    }

    /**
     * Update the cover of a book
     * @param bookId the id of the book
     * @param coverUriDTO the new cover uri
     */
    @Override
    public void updateBookCover(String bookId, CoverUriDTO coverUriDTO) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if(bookOptional.isPresent()) {
            Book book = bookOptional.get();
            book.setCover(coverUriDTO);
            bookRepository.save(book);
        }
    }

    /**
     * Get all books of an author
     * @return a list of all books of an author
     */
    @Override
    public List<BookDTO> getBooksByAuthor() {
        String userId = userService.getCurrentUser();
        List<Book> books = bookRepository.findByAuthor(userId);
        return books.stream()
                .map(bookMapper::toBookDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get a book with only the published chapters
     * @param bookId the id of the book
     * @return the book with only the published chapters
     */
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

    /**
     * Increase the likes of a book
     * @param bookId the id of the book
     */
    @Override
    public void increaseBookLikes(String bookId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if(bookOptional.isPresent()) {
            Book book = bookOptional.get();
            book.setLikes(book.getLikes() + 1);
            bookRepository.save(book);
        }
    }

    /**
     * Decrease the likes of a book
     * @param bookId the id of the book
     */
    @Override
    public void decreaseBookLikes(String bookId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if(bookOptional.isPresent()) {
            Book book = bookOptional.get();
            book.setLikes(book.getLikes() - 1);
            bookRepository.save(book);
        }
    }

    /**
     * Get the progress of a book
     * @param bookId the id of the book to get the progress of
     * @return the progress of the book
     */
    @Override
    public int getBookProgress(String bookId) {
        Optional<Progress> progress = this.progressRepository.findByBookIdAndUser(bookId, userService.getCurrentUser());
        return progress.map(Progress::getReadChapters).orElse(0);
    }

    /**
     * Increase the progress of a book
     * @param bookId the id of the book
     * @param progress the progress to increase
     */
    @Override
    public void increaseProgress(String bookId, int progress) {
        Optional<Progress> progressOptional = this.progressRepository.findByBookIdAndUser(bookId, userService.getCurrentUser());
        if(progressOptional.isPresent()) {
            Progress progressObject = progressOptional.get();
            int maxChapters = getBookWithPublishedChapters(bookId).getChapterIds().size();
            if (progressObject.getReadChapters() >= maxChapters) {
                return;
            }
            progressObject.setReadChapters(progress);
            this.progressRepository.save(progressObject);
        } else {
            Progress progressObject = new Progress();
            progressObject.setBookId(bookId);
            progressObject.setUser(userService.getCurrentUser());
            progressObject.setReadChapters(1);
            this.progressRepository.save(progressObject);
        }
    }
}
