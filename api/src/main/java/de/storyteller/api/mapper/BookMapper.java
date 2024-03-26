package de.storyteller.api.mapper;

import de.storyteller.api.dto.book.AddBookRequest;
import de.storyteller.api.dto.book.BookDTO;
import de.storyteller.api.dto.book.EditBookRequest;
import de.storyteller.api.dto.chapter.ChapterDTO;
import de.storyteller.api.model.Book;
import de.storyteller.api.model.Chapter;
import de.storyteller.api.repository.BookRepository;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Mapper(componentModel = "spring")
public abstract class BookMapper {

    @Autowired
    protected BookRepository bookRepository;
    @Autowired
    protected ChapterMapper chapterMapper;

    public abstract BookDTO toBookDTO(Book book);

    public abstract Book toBook(BookDTO bookDTO);

    public abstract Book toBook(AddBookRequest addBookRequest);
    public abstract Book toBook(EditBookRequest editBookRequest);

    public List<ChapterDTO> mapChaptersToDTOs(Set<Chapter> chapters) {
        return chapters.stream()
                .map(chapterMapper::toChapterDTO)
                .collect(Collectors.toList());
    }

    public abstract ChapterDTO toChapterDTO(Chapter chapter);
}