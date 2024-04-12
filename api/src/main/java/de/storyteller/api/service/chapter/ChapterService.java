package de.storyteller.api.service.chapter;

import de.storyteller.api.dto.book.BookDTO;
import de.storyteller.api.dto.chapter.AddChapterRequest;
import de.storyteller.api.dto.chapter.ChapterDTO;
import de.storyteller.api.dto.chapter.EditChapterRequest;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ChapterService {

    ChapterDTO createChapter(AddChapterRequest chapter);

    Optional<ChapterDTO> getChapterById(String chapterId);

    ChapterDTO updateChapter(EditChapterRequest chapter);


    List<ChapterDTO> getAllChapters();
}
