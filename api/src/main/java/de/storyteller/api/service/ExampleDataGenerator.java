package de.storyteller.api.service;

import de.storyteller.api.model.Book;
import de.storyteller.api.model.Genre;
import de.storyteller.api.repository.BookRepository;
import de.storyteller.api.repository.GenreRepository;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExampleDataGenerator {
  @Autowired
  GenreRepository genreRepository;
  @Autowired
  BookRepository bookRepository;

  public void generate(){
    genreRepository.save(new Genre("1b8b0883-596d-42b0-be60-b8793f22a572","Fantasy"));
    for (int i = 0; i < 10; i++) {
      bookRepository.save(new Book(
          UUID.randomUUID().toString(),
          "Title " + i,
          genreRepository.findByName("Fantasy"),
          UUID.randomUUID().toString(),
          "description " + i,
          "catch " + i,
              List.of(),
              List.of("tag " + i, "tag " + (i + 1)),
          "no cover " + i,
            true, false, false, false
      ));
    }
  }
}
