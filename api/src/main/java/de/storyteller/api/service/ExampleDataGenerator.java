package de.storyteller.api.service;

import de.storyteller.api.service.genre.GenreService;
import de.storyteller.api.v1.dto.cover.CoverUriDTO;
import de.storyteller.api.model.Book;
import de.storyteller.api.model.Genre;
import de.storyteller.api.repository.BookRepository;
import de.storyteller.api.repository.GenreRepository;

import java.util.List;
import java.util.UUID;

import de.storyteller.api.v1.dto.genre.AddGenreRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for generating example data.
 */
@Service
public class ExampleDataGenerator {
  @Autowired
  GenreRepository genreRepository;
  @Autowired
  BookRepository bookRepository;

  @Autowired
  GenreService genreService;

  /**
   * Generate example data.
   */
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
          new CoverUriDTO("no cover" + i,"no cover" + i,"no cover" + i),
            true, false, false, false, 0
      ));
    }
  }

  /**
   * Generate genres.
   */
  public void generateGenres(){
    genreService.createGenre(new AddGenreRequest("Fantasy"));
    genreService.createGenre(new AddGenreRequest("Distopian"));
    genreService.createGenre(new AddGenreRequest("Mystery"));
    genreService.createGenre(new AddGenreRequest("Science Fiction"));
    genreService.createGenre(new AddGenreRequest("Romance"));
    genreService.createGenre(new AddGenreRequest("Horror"));
    genreService.createGenre(new AddGenreRequest("Thriller"));
    genreService.createGenre(new AddGenreRequest("Historical Fiction"));
    genreService.createGenre(new AddGenreRequest("Non-Fiction"));
    genreService.createGenre(new AddGenreRequest("Biography"));
    genreService.createGenre(new AddGenreRequest("Contemporary"));
    genreService.createGenre(new AddGenreRequest("Young Adult"));
    genreService.createGenre(new AddGenreRequest("New Adult"));
    genreService.createGenre(new AddGenreRequest("Essays"));
    genreService.createGenre(new AddGenreRequest("Poetry"));
    genreService.createGenre(new AddGenreRequest("Short Stories"));
    genreService.createGenre(new AddGenreRequest("Fan Fiction"));

  }
}
