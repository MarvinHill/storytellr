package de.storyteller.api.repository;

import de.storyteller.api.model.Library;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface LibraryRepository extends MongoRepository<Library, String> {
    Optional<Library> findByOwnerId(String userId);
}
