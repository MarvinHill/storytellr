package de.storyteller.api.service.genre;

import de.storyteller.api.v1.dto.genre.AddGenreRequest;
import de.storyteller.api.v1.dto.genre.GenreDTO;
import de.storyteller.api.v1.mapper.GenreMapper;
import de.storyteller.api.model.Genre;
import de.storyteller.api.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the GenreService
 */
@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService{
    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    /**
     * Create a genre
     * @param genre the genre to create
     * @return the created genre
     */
    @Override
    public GenreDTO createGenre(AddGenreRequest genre) {
        return genreMapper.toGenreDTO(genreRepository.save(genreMapper.toGenre(genre)));
    }

    /**
     * Delete a genre by its id
     * @param id the id of the genre
     */
    @Override
    public void deleteGenre(String id) {
        if(!genreRepository.existsById(id)){
            throw new RuntimeException("Genre with id: " + id + " doesn't exist");
        }
        genreRepository.deleteById(id);
    }

    /**
     * Get all genres
     * @return a list of all genres
     */
    @Override
    public List<GenreDTO> getAllGenres() {
            List<Genre> genres = genreRepository.findAll();
            return genres.stream().map(genreMapper::toGenreDTO).collect(Collectors.toList());
    }

    /**
     * Get a genre by its id
     * @param id the id of the genre
     * @return the genre
     */
    @Override
    public GenreDTO getGenreById(String id) {
        return genreMapper.toGenreDTO(genreRepository.findById(id).orElseThrow(() -> new RuntimeException("Genre with id: " + id + " doesn't exist")));
    }
}
