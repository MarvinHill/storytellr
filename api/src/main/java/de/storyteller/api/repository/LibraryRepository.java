package de.storyteller.api.repository;

import de.storyteller.api.model.Library;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * Repository for libraries
 */
public interface LibraryRepository extends MongoRepository<Library, String> {
    /**
     * Find a library by owner id
     * @param userId the owner id
     * @return the library belonging to the owner if it exists
     */
    Optional<Library> findByOwnerId(String userId);
}
