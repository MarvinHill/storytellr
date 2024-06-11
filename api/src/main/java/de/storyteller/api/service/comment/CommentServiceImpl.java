package de.storyteller.api.service.comment;

import de.storyteller.api.model.Chapter;
import de.storyteller.api.model.Comment;
import de.storyteller.api.repository.ChapterRepository;
import de.storyteller.api.v1.dto.comment.AddCommentRequest;
import de.storyteller.api.v1.dto.comment.CommentDTO;
import de.storyteller.api.v1.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final ChapterRepository chapterRepository;

    private final CommentMapper commentMapper;


    @Override
    public CommentDTO addComment(AddCommentRequest addCommentRequest) {
        Chapter chapter = this.chapterRepository.findById(addCommentRequest.getChapterId()).orElseThrow(() -> new RuntimeException("Chapter not found"));
        Comment comment = this.commentMapper.toComment(addCommentRequest);
        chapter.getComments().add(comment);
        this.chapterRepository.save(chapter);
        return this.commentMapper.toDTO(comment);
    }

    @Override
    public List<CommentDTO> getCommentsByChapter(String chapterId) {
        Chapter chapter = this.chapterRepository.findById(chapterId).orElseThrow(() -> new RuntimeException("Chapter not found"));
        return chapter.getComments().stream().map(this.commentMapper::toDTO).toList();
    }


}
