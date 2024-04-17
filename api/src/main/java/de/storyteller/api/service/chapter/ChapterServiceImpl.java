package de.storyteller.api.service.chapter;


import de.storyteller.api.v1.dto.chapter.AddChapterRequest;
import de.storyteller.api.v1.dto.chapter.ChapterDTO;
import de.storyteller.api.v1.dto.chapter.EditChapterRequest;
import de.storyteller.api.v1.mapper.ChapterMapper;
import de.storyteller.api.model.Chapter;
import de.storyteller.api.repository.BookRepository;
import de.storyteller.api.repository.ChapterRepository;
import de.storyteller.api.model.Book;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ChapterServiceImpl implements ChapterService{
    private final ChapterRepository chapterRepository;
    private final BookRepository bookRepository;
    private final ChapterMapper chapterMapper;
    @Override
    public ChapterDTO createChapter(AddChapterRequest chapter) {
        if (!bookRepository.existsById(chapter.getBookId())) {
            throw new RuntimeException("Book with id: " + chapter.getBookId() + " doesn't exist");
        }
        Book book = bookRepository.findById(chapter.getBookId()).get();
        ChapterDTO savedChapter = chapterMapper.toChapterDTO(chapterRepository.save(chapterMapper.toChapter(chapter)));
        book.getChapters().add(chapterMapper.toChapter(savedChapter));
        bookRepository.save(book);
        return savedChapter;
    }

    @Override
    public Optional<ChapterDTO> getChapterById(String chapterId) {
        Optional<Chapter> chapterOptional = chapterRepository.findById(chapterId);
        return chapterOptional.isPresent() ? Optional.of(chapterMapper.toChapterDTO(chapterOptional.get())) : Optional.empty();
    }

    @Override
    public ChapterDTO updateChapter(EditChapterRequest chapter) {
        if (!chapterRepository.existsById(chapter.getId())) {
            throw new RuntimeException("Chapter with id: " + chapter.getId() + " doesn't exist");
        }
        return chapterMapper.toChapterDTO(chapterRepository.save(chapterMapper.toChapter(chapter)));
    }

    @Override
    public List<ChapterDTO> getAllChapters() {
        List<Chapter> chapters = chapterRepository.findAll();
        return chapters.stream()
                .map(chapterMapper::toChapterDTO)
                .collect(Collectors.toList());
    }
}
