package de.storyteller.api.repository;

import de.storyteller.api.model.Book;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

/**
 * Repository for books
 */
public interface BookRepository extends MongoRepository<Book, String> {

    /**
     * Find all books by author
     * @param userId the author id
     * @return the all books belonging to the author
     */
    List<Book> findByAuthor(String userId);
}
