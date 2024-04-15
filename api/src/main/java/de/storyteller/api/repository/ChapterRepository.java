package de.storyteller.api.repository;


import de.storyteller.api.model.Chapter;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface ChapterRepository extends MongoRepository<Chapter, String> {

}
