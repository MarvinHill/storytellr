package de.storyteller.api.repository;

import de.storyteller.api.model.Progress;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * Repository for progress
 */
public interface ProgressRepository extends MongoRepository<Progress, String> {

    /**
     * Find progress by book id and user
     * @param bookId the book id
     * @param userId the user id
     * @return the progress if it exists
     */
    Optional<Progress> findByBookIdAndUser(String bookId, String userId);
}
