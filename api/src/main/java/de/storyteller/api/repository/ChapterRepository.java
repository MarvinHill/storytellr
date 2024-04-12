package de.storyteller.api.repository;

import de.storyteller.api.model.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ChapterRepository extends JpaRepository<Chapter, UUID> {
    List<Chapter> findAllByBookId(UUID bookId);
}
