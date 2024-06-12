package de.storyteller.api.repository;

import de.storyteller.api.model.Book;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface BookRepository extends MongoRepository<Book, String> {

    List<Book> findByAuthor(String userId);
}
