package de.storyteller.api.mapper;

import de.storyteller.api.dto.book.AddBookRequest;
import de.storyteller.api.dto.book.BookDTO;
import de.storyteller.api.dto.book.EditBookRequest;
import de.storyteller.api.dto.chapter.ChapterDTO;
import de.storyteller.api.model.Book;
import de.storyteller.api.model.Chapter;
import de.storyteller.api.model.Genre;
import de.storyteller.api.repository.BookRepository;
import de.storyteller.api.repository.ChapterRepository;
import de.storyteller.api.repository.GenreRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @Mapping(target = "genreId", source = "genre.id")
    @Mapping(target = "chapterIds", source = "chapters")
    public abstract BookDTO toBookDTO(Book book);

    @Mapping(target = "genre", source = "genreId")
    @Mapping(target = "chapters", source = "chapterIds")
    public abstract Book toBook(BookDTO bookDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "genre", source = "genreId")
    @Mapping(target = "chapters", source = "chapterIds")
    public abstract Book toBook(AddBookRequest addBookRequest);
    @Mapping(target = "genre", source = "genreId")
    @Mapping(target = "chapters", source = "chapterIds")
    public abstract Book toBook(EditBookRequest editBookRequest);
    protected Genre mapGenreIdToGenre(String genreId) {
        return genreRepository.findById(genreId).orElse(null);
    }
    List<String> mapIdsToChapters(List<Chapter> chapters) {
        return chapters.stream().map(Chapter::getId).collect(Collectors.toList());
    }
    List<Chapter> mapChaptersToId(List<String> chapterIds) {
        return chapterIds.stream().map(chapterRepository::findById).filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());
    }
}