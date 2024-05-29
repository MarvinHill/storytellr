package de.storyteller.api.repository;

import de.storyteller.api.model.Progress;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProgressRepository extends MongoRepository<Progress, String> {

    Optional<Progress> findByBookIdAndUser(String bookId, String userId);
}
