package de.storyteller.api.auth;

import de.storyteller.api.dto.book.BookDTO;
import de.storyteller.api.dto.chapter.ChapterDTO;
import de.storyteller.api.service.book.BookService;
import de.storyteller.api.service.chapter.ChapterService;

import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service("sAuthService")
@Slf4j
public class CustomAuthorizationService {
    @Autowired
    BookService bookService;

    @Autowired
    ChapterService chapterService;

    public boolean userOwnsBook(String bookId){
      Optional<BookDTO> bookDTO = bookService.getBookById(bookId);
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      if(bookDTO.isEmpty() || authentication == null) return false;
      Jwt jwt = (Jwt) authentication.getPrincipal();

      return jwt.getSubject().equals(bookDTO.get().getAuthor().toString());
    }

//    public boolean userIsAuthorOfChapter(String chapterId){
//      Optional<ChapterDTO> chapterDTO = chapterService.getChapterById(chapterId);
//      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//      if(chapterDTO.isEmpty() || authentication == null) return false;
//      Jwt jwt = (Jwt) authentication.getPrincipal();
//
//      Optional<BookDTO> bookDTO = bookService.getBookById(chapterDTO.get().getBookId());
//      if(bookDTO.isEmpty()) return false;
//
//      return jwt.getSubject().equals(bookDTO.get().getAuthor().toString());
//    }

    public boolean userIsAuthorOfChapter(String chapterId){
      Optional<ChapterDTO> chapterDTO = chapterService.getChapterById(chapterId);
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      if(chapterDTO.isEmpty() || authentication == null) return false;
      Jwt jwt = (Jwt) authentication.getPrincipal();

        List<BookDTO> books = bookService.getAllBooks();
        for(BookDTO book : books){
            for(String chapter : book.getChapterIds()){
                if(chapter.equals(chapterId)){
                    return jwt.getSubject().equals(book.getAuthor().toString());
                }
            }
        }
        return false;
    }

    public boolean testLog(){
      return true;
    }
}
