package de.storyteller.api.mapper;

import de.storyteller.api.dto.BookDTO;
import de.storyteller.api.model.Book;
import de.storyteller.api.model.Chapter;
import de.storyteller.api.repository.BookRepository;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@Mapper(componentModel = "spring")
public abstract class BookMapper {

    @Autowired
    protected BookRepository bookRepository;

    public abstract BookDTO bookToBookDTO(Book book);

    public abstract Book bookDTOToBook(BookDTO bookDTO);

    protected Set<Long> mapChaptersToIds(Set<Chapter> chapters) {
        return chapters.stream()
                .map(Chapter::getId)
                .collect(Collectors.toSet());
    }
}