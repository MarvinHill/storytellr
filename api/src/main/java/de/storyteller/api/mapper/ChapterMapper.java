package de.storyteller.api.mapper;

import de.storyteller.api.dto.book.BookDTO;
import de.storyteller.api.dto.chapter.AddChapterRequest;
import de.storyteller.api.dto.chapter.ChapterDTO;
import de.storyteller.api.dto.chapter.EditChapterRequest;
import de.storyteller.api.model.Book;
import de.storyteller.api.model.Chapter;
import de.storyteller.api.repository.BookRepository;
import de.storyteller.api.repository.ChapterRepository;
import org.json.JSONObject;
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

    private final String ID_PLACEHOLDER = "550e8400-e29b-41d4-a716-446655440000";

    @Mapping(target = "bookId", source = "book.id")
    public abstract ChapterDTO toChapterDTO(Chapter chapter);
    @Mapping(target = "book", source = "bookId")
    @Mapping(target = "contentFromJSONObject", ignore = true)
    public abstract Chapter toChapter(ChapterDTO chapterDTO);
    @Mapping(target = "book", source = "bookId")
    @Mapping(target = "id", constant = ID_PLACEHOLDER)
    @Mapping(target = "contentFromJSONObject", ignore = true)
    public abstract Chapter toChapter(AddChapterRequest chapter);
    @Mapping(target = "book", source = "bookId")
    @Mapping(target = "contentFromJSONObject", ignore = true)
    public abstract Chapter toChapter(EditChapterRequest chapter);
    protected Book mapBookIdToBook(UUID bookId) {
        return bookRepository.findById(bookId).orElse(null);
    }
    public JSONObject getContentAsJSONObject(String content) {
        return new JSONObject(content);
    }

    public String setContentFromJSONObject(JSONObject jsonObject) {
        return jsonObject.toString();
    }

}