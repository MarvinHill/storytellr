package de.storyteller.api.service.comment;

import de.storyteller.api.model.Book;
import de.storyteller.api.model.Chapter;
import de.storyteller.api.model.Comment;
import de.storyteller.api.repository.BookRepository;
import de.storyteller.api.repository.ChapterRepository;
import de.storyteller.api.v1.dto.comment.AddCommentRequest;
import de.storyteller.api.v1.dto.comment.CommentDTO;
import de.storyteller.api.v1.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;


import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final ChapterRepository chapterRepository;

    private final CommentMapper commentMapper;
    private final BookRepository bookRepository;


    @Override
    public CommentDTO addComment(AddCommentRequest addCommentRequest) {
        Chapter chapter = this.chapterRepository.findById(addCommentRequest.getChapterId()).orElseThrow(() -> new RuntimeException("Chapter not found"));
        Comment comment = this.commentMapper.toComment(addCommentRequest);
        // Return if comments are deactivated
        Book book = this.bookRepository.findById(chapter.getBookId()).orElseThrow(() -> new RuntimeException("Book not found"));
        if (!book.isCommentsDeactivated()) {
            throw new RuntimeException("Comments are deactivated for this book");
        }

        chapter.getComments().add(comment);
        this.chapterRepository.save(chapter);
        return this.commentMapper.toDTO(comment);
    }

    @Override
    public List<CommentDTO> getCommentsByChapter(String chapterId) {
        Chapter chapter = this.chapterRepository.findById(chapterId).orElseThrow(() -> new RuntimeException("Chapter not found"));
        // Return if comments are deactivated
        Book book = this.bookRepository.findById(chapter.getBookId()).orElseThrow(() -> new RuntimeException("Book not found"));
        if (!book.isCommentsDeactivated()) {
            return List.of();
        }
        return chapter.getComments().stream().map(this.commentMapper::toDTO).toList();
    }

    @Override
    public List<CommentDTO> getCommentsByBlockId(String chapterId, String blockId) {
        Chapter chapter = this.chapterRepository.findById(chapterId).orElseThrow(() -> new RuntimeException("Chapter not found"));
        // Return if comments are deactivated
        Book book = this.bookRepository.findById(chapter.getBookId()).orElseThrow(() -> new RuntimeException("Book not found"));
        if (!book.isCommentsDeactivated()) {
            return List.of();
        }
        return chapter.getComments().stream().filter(comment -> comment.getBlockId().equals(blockId)).map(this.commentMapper::toDTO).toList();
    }


}
