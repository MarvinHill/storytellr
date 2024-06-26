package de.storyteller.api.service.chapter;

import de.storyteller.api.v1.dto.chapter.AddChapterRequest;
import de.storyteller.api.v1.dto.chapter.ChapterDTO;
import de.storyteller.api.v1.dto.chapter.EditChapterRequest;

import java.util.List;
import java.util.Optional;

/**
 * Service for chapters
 */
public interface ChapterService {

    /**
     * Create a chapter
     * @param chapter the chapter to create
     * @return the created chapter
     */
    ChapterDTO createChapter(AddChapterRequest chapter);

    /**
     * Get a chapter by its id
     * @param chapterId the id of the chapter
     * @return the chapter with the given id
     */
    Optional<ChapterDTO> getChapterById(String chapterId);

    /**
     * Update a chapter
     * @param chapter the chapter to update
     * @return the updated chapter
     */
    ChapterDTO updateChapter(EditChapterRequest chapter);

    /**
     * Get all chapters
     * @return a list of all chapters
     */
    List<ChapterDTO> getAllChapters();

    /**
     * Get all chapters of a book
     * @param bookId the id of the book
     * @return a list of all chapters of the book
     */
    List<ChapterDTO> getChaptersByBookId(String bookId);

    /**
     * Get all published chapters of a book
     * @param bookId the id of the book
     * @return a list of all published chapters of the book
     */
    List<ChapterDTO> getPublishedChaptersByBookId(String bookId);

    /**
     * Get the first n published chapters of a book
     * @param bookId the id of the book
     * @param count the number of chapters to get
     * @return a list of the first n published chapters of the book
     */
    List<ChapterDTO> getFirstNPublishedChapters(String bookId, int count);
}
