package de.storyteller.api.v1.mapper;

import de.storyteller.api.v1.dto.book.AddBookRequest;
import de.storyteller.api.v1.dto.book.BookDTO;
import de.storyteller.api.v1.dto.book.EditBookRequest;
import de.storyteller.api.model.Book;
import de.storyteller.api.model.Genre;
import de.storyteller.api.repository.BookRepository;
import de.storyteller.api.repository.GenreRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Mapper(componentModel = "spring")
public abstract class BookMapper {

    @Autowired
    protected BookRepository bookRepository;
    @Autowired
    protected ChapterMapper chapterMapper;

    @Autowired
    protected GenreRepository genreRepository;

    @Mapping(target = "genreId", source = "genre.id")
    public abstract BookDTO toBookDTO(Book book);

    @Mapping(target = "genre", source = "genreId")
    public abstract Book toBook(BookDTO bookDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "genre", source = "genreId")
    @Mapping(target = "cover", ignore = true)
    public abstract Book toBook(AddBookRequest addBookRequest);

    @Mapping(target = "genreId", source = "genre")
    public abstract AddBookRequest toAddBookRequest( Book book);

    public abstract EditBookRequest toEditBookRequest( BookDTO bookDTO);

    @Mapping(target = "cover", ignore = true)
    public abstract BookDTO toBookDTO(EditBookRequest editBookRequest);

    @Mapping(target = "genre", source = "genreId")
    @Mapping(target = "cover", ignore = true)
    public abstract Book toBook(EditBookRequest editBookRequest);

    protected Genre mapGenreIdToGenre(UUID genreId) {
        return genreRepository.findById(genreId).orElse(null);
    }

    protected UUID mapGenreIdToGenre(Genre genre) {
        return genre.getId();
    }

}