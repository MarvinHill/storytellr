package de.storyteller.api.repository;

import de.storyteller.api.model.Library;
import de.storyteller.api.model.Poll;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository for polls
 */
public interface PollRepository extends MongoRepository<Poll, String> {}
