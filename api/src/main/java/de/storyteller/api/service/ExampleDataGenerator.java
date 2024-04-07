package de.storyteller.api.service;

import de.storyteller.api.model.Book;
import de.storyteller.api.model.Genre;
import de.storyteller.api.repository.BookRepository;
import de.storyteller.api.repository.GenreRepository;
import java.util.UUID;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExampleDataGenerator {
  @Autowired
  GenreRepository genreRepository;
  @Autowired
  BookRepository bookRepository;

  public void generate(){
    genreRepository.save(new Genre(UUID.fromString("1b8b0883-596d-42b0-be60-b8793f22a572"),"Fantasy"));
    for (int i = 0; i < 10; i++) {
      bookRepository.save(new Book(
          UUID.randomUUID(),
          "Title " + i,
          genreRepository.findByName("Fantasy"),
          UUID.randomUUID().toString(),
          "description " + i,
          "catch " + i,
          "no cover " + i
      ));
    }
  }
}