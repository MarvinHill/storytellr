package de.storyteller.api.service.genre;

import de.storyteller.api.dto.genre.AddGenreRequest;
import de.storyteller.api.dto.genre.GenreDTO;
import de.storyteller.api.mapper.GenreMapper;
import de.storyteller.api.model.Book;
import de.storyteller.api.model.Genre;
import de.storyteller.api.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService{
    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    @Override
    public GenreDTO createGenre(AddGenreRequest genre) {
        return genreMapper.toGenreDTO(genreRepository.save(genreMapper.toGenre(genre)));
    }


    @Override
    public void deleteGenre(String id) {
        if(!genreRepository.existsById(id)){
            throw new RuntimeException("Genre with id: " + id + " doesn't exist");
        }
        genreRepository.deleteById(id);
    }

    @Override
    public List<GenreDTO> getAllGenres() {
            List<Genre> genres = genreRepository.findAll();
            return genres.stream().map(genreMapper::toGenreDTO).collect(Collectors.toList());
    }

    @Override
    public GenreDTO getGenreById(String id) {
        return genreMapper.toGenreDTO(genreRepository.findById(id).orElseThrow(() -> new RuntimeException("Genre with id: " + id + " doesn't exist")));
    }
}
