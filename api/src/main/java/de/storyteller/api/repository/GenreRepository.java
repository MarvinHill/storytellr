package de.storyteller.api.repository;

import de.storyteller.api.model.Genre;

import org.springframework.data.mongodb.repository.MongoRepository;



/**
 * Repository for genres
 */
public interface GenreRepository extends MongoRepository<Genre, String> {
  Genre findByName(String name);
}
