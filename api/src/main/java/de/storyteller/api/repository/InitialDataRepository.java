package de.storyteller.api.repository;

import de.storyteller.api.model.DbInitialized;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository for saving the state if the intial data has been inserted
 * */
public interface InitialDataRepository extends MongoRepository<DbInitialized, String> {
}
