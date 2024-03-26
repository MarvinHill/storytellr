package de.storyteller.api.repository;

import de.storyteller.api.dto.chapter.ChapterDTO;
import de.storyteller.api.model.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChapterRepository extends JpaRepository<Chapter, Long> {
    List<Chapter> findAllByBookId(Long bookId);
}
