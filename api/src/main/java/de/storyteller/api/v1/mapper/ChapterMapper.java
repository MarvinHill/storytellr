package de.storyteller.api.v1.mapper;

import de.storyteller.api.v1.dto.chapter.AddChapterRequest;
import de.storyteller.api.v1.dto.chapter.ChapterDTO;
import de.storyteller.api.v1.dto.chapter.EditChapterRequest;
import de.storyteller.api.model.Book;
import de.storyteller.api.model.Chapter;
import de.storyteller.api.repository.BookRepository;
import de.storyteller.api.repository.ChapterRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



/**
 * Mapper for chapters
 */
@Component
@Mapper(componentModel = "spring")
public abstract class ChapterMapper {
    @Autowired
    protected ChapterRepository chapterRepository;
    @Autowired
    protected BookRepository bookRepository;


    /**
     * Maps a chapter to a chapterDTO
     * @param chapter the chapter to map
     * @return the mapped chapterDTO
     */
    public abstract ChapterDTO toChapterDTO(Chapter chapter);

    /**
     * Maps a chapterDTO to a chapter
     * @param chapterDTO the chapterDTO to map
     * @return the mapped chapter
     */
    public abstract Chapter toChapter(ChapterDTO chapterDTO);

    /**
     * Maps a AddChapterRequest to a chapter
     * @param chapter the chapter to map
     * @return the mapped chapter
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "content", constant = "")
    @Mapping(target = "comments", ignore = true)
    public abstract Chapter toChapter(AddChapterRequest chapter);

    /**
     * Maps a EditChapterRequest to a chapter
     * @param editChapterRequest the chapter to map
     * @return the mapped chapter
     */
    @Mapping(target = "comments", expression = "java(chapterRepository.findById(editChapterRequest.getId()).orElseThrow().getComments())")
    public abstract Chapter toChapter(EditChapterRequest editChapterRequest);

    /**
     * Maps a bookId to a book
     * @param bookId the bookId to map
     * @return the mapped book
     */
    protected Book mapBookIdToBook(String bookId) {
        return bookRepository.findById(bookId).orElse(null);
    }
}
