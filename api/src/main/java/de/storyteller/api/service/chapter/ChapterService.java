package de.storyteller.api.service.chapter;

import de.storyteller.api.v1.dto.chapter.AddChapterRequest;
import de.storyteller.api.v1.dto.chapter.ChapterDTO;
import de.storyteller.api.v1.dto.chapter.EditChapterRequest;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ChapterService {
    @Transactional
    ChapterDTO createChapter(AddChapterRequest chapter);
    @Transactional
    Optional<ChapterDTO> getChapterById(UUID chapterId);
    @Transactional
    ChapterDTO updateChapter(EditChapterRequest chapter);

    @Transactional
    List<ChapterDTO> getAllChapters();
}
