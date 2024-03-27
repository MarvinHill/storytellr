package de.storyteller.api.mapper;

import de.storyteller.api.dto.book.BookDTO;
import de.storyteller.api.dto.chapter.AddChapterRequest;
import de.storyteller.api.dto.chapter.ChapterDTO;
import de.storyteller.api.dto.chapter.EditChapterRequest;
import de.storyteller.api.model.Book;
import de.storyteller.api.model.Chapter;
import de.storyteller.api.repository.BookRepository;
import de.storyteller.api.repository.ChapterRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Mapper(componentModel = "spring")
public abstract class ChapterMapper {
    @Autowired
    protected ChapterRepository chapterRepository;
    @Autowired
    protected BookRepository bookRepository;

    @Mapping(target = "bookId", source = "id")
    public abstract ChapterDTO toChapterDTO(Chapter chapter);
    public abstract Chapter toChapter(ChapterDTO chapterDTO);
    @Mapping(target = "book", source = "bookId")
    public abstract Chapter toChapter(AddChapterRequest chapter);
    public abstract Chapter toChapter(EditChapterRequest chapter);
    protected Book mapBookIdToBook(UUID bookId) {
        return bookRepository.findById(bookId).orElse(null);
    }

}
