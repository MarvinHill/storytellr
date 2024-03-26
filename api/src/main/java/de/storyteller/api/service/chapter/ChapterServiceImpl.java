package de.storyteller.api.service.chapter;


import de.storyteller.api.dto.chapter.AddChapterRequest;
import de.storyteller.api.dto.chapter.ChapterDTO;
import de.storyteller.api.dto.chapter.EditChapterRequest;
import de.storyteller.api.mapper.ChapterMapper;
import de.storyteller.api.model.Chapter;
import de.storyteller.api.repository.ChapterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ChapterServiceImpl implements ChapterService{
    private final ChapterRepository chapterRepository;
    private final ChapterMapper chapterMapper;
    @Override
    public ChapterDTO createChapter(AddChapterRequest chapter) {
        return chapterMapper.toChapterDTO(chapterRepository.save(chapterMapper.toChapter(chapter)));
    }

    @Override
    public ChapterDTO updateChapter(EditChapterRequest chapter) {
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
