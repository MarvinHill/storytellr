package de.storyteller.api.service.chapter;

import de.storyteller.api.dto.book.BookDTO;
import de.storyteller.api.dto.chapter.AddChapterRequest;
import de.storyteller.api.dto.chapter.ChapterDTO;
import de.storyteller.api.dto.chapter.EditChapterRequest;
import jakarta.transaction.Transactional;

import java.util.List;

public interface ChapterService {
    @Transactional
    ChapterDTO createChapter(AddChapterRequest chapter);
    @Transactional
    ChapterDTO updateChapter(EditChapterRequest chapter);

    @Transactional
    List<ChapterDTO> getAllChapters();
}
