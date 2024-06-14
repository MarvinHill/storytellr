package de.storyteller.api.v1.mapper;

import de.storyteller.api.model.Chapter;
import de.storyteller.api.service.UserService;
import de.storyteller.api.service.auth.KeycloakService;
import de.storyteller.api.v1.dto.book.AddBookRequest;
import de.storyteller.api.v1.dto.book.BookDTO;
import de.storyteller.api.v1.dto.book.EditBookRequest;
import de.storyteller.api.model.Book;
import de.storyteller.api.model.Genre;
import de.storyteller.api.repository.BookRepository;
import de.storyteller.api.repository.ChapterRepository;
import de.storyteller.api.repository.GenreRepository;
import de.storyteller.api.v1.dto.cover.CoverUtils;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Mapper for books
 */
@Component
@Mapper(componentModel = "spring")
public abstract class BookMapper {
    @Autowired
    protected BookRepository bookRepository;
    @Autowired
    protected ChapterMapper chapterMapper;
    @Autowired
    protected ChapterRepository chapterRepository;
    @Autowired
    protected GenreRepository genreRepository;
    @Autowired
    @Qualifier("CachedKeycloakService")
    protected KeycloakService keycloakService;
    @Autowired
    protected CoverUtils coverUtils;
    @Autowired
    protected UserService userService;
    /**
     * Maps a book to a bookDTO
     * @param book the book to map
     * @return the mapped bookDTO
     */
    @Mapping(target = "genreId", source = "genre.id")
    @Mapping(target = "chapterIds", source = "chapters")
    @Mapping(target = "authorName", expression = "java(keycloakService.getUsername(book.getAuthor()))")
    public abstract BookDTO toBookDTO(Book book);

    /**
     * Maps a bookDTO to a book
     * @param bookDTO the bookDTO to map
     * @return the mapped book
     */
    @Mapping(target = "genre", source = "genreId")
    @Mapping(target = "chapters", source = "chapterIds")
    public abstract Book toBook(BookDTO bookDTO);

    /**
     * Maps a AddBookRequest to a book
     * @param addBookRequest the book to map
     * @return the mapped book
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "genre", source = "genreId")
    @Mapping(target = "chapters", source = "chapterIds")
    @Mapping(target = "cover", expression = "java(coverUtils.defaultCover())")
    @Mapping(target = "author", expression = "java(userService.getUserId())")
    @Mapping(target = "likes", constant = "0")
    public abstract Book toBook(AddBookRequest addBookRequest);

    /**
     * Maps the genre to a genreId
     * @param genre the genre to map
     * @return the mapped genreId
     */
    private String toGenreId(Genre genre){
        return genre.getId();
    }

    /**
     * Maps a EditBookRequest to a book
     * @param editBookRequest the book to map
     * @return the mapped book
     */
    @Mapping(target = "genre", source = "genreId")
    @Mapping(target = "chapters", source = "chapterIds")
    @Mapping(target = "cover", expression = "java(bookRepository.findById(editBookRequest.getId()).get().getCover())")
    @Mapping(target = "author", expression = "java(bookRepository.findById(editBookRequest.getId()).get().getAuthor())")
    @Mapping(target = "likes", expression = "java(bookRepository.findById(editBookRequest.getId()).get().getLikes())")
    public abstract Book toBook(EditBookRequest editBookRequest);

    /**
     * Maps the GenreId to a Genre
     * @param genreId the genreId to map
     * @return the mapped genre
     */
    protected Genre mapGenreIdToGenre(String genreId) {
        return genreRepository.findById(genreId).orElse(null);

    }

    /**
     * Maps the chapterIds to a list of chapters
     * @param chapters the chapterIds to map
     * @return the mapped chapters
     */
    List<String> mapIdsToChapters(List<Chapter> chapters) {
        if(chapters == null) {
            return null;
        }
        return chapters.stream().map(Chapter::getId).collect(Collectors.toList());
    }

    /**
     * Maps the chapters to a list of chapterIds
     * @param chapterIds the chapterIds to map
     * @return the mapped chapters
     */
    List<Chapter> mapChaptersToId(List<String> chapterIds) {
        if(chapterIds == null) {
            return null;
        }
        return chapterIds.stream().map(chapterRepository::findById).filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());
    }
}