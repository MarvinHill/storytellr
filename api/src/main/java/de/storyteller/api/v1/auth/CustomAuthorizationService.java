package de.storyteller.api.v1.auth;

import de.storyteller.api.v1.dto.book.BookDTO;
import de.storyteller.api.v1.dto.chapter.ChapterDTO;
import de.storyteller.api.service.book.BookService;
import de.storyteller.api.service.chapter.ChapterService;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service("sAuthService")
@Slf4j
public class CustomAuthorizationService {
    BookService bookService;
    ChapterService chapterService;
    AuthUtils authUtils;

    public boolean userOwnsBook(String bookId){
      Optional<BookDTO> bookDTO = bookService.getBookById(bookId);
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      if(bookDTO.isEmpty() || authentication == null) return false;
      Jwt jwt = (Jwt) authentication.getPrincipal();

      return jwt.getSubject().equals(bookDTO.get().getAuthor().toString());
    }

    public boolean isAdmin(){
      try {
        Map<String, Object> claims = authUtils.getClaims();
        Map<String,Object> realmAccess = (Map<String, Object>) claims.get("realm_access");
        ArrayList<String> roles = (ArrayList<String>) realmAccess.get("roles");

         for (String role : roles) {
           if(role.equals("admin-storytellr")){
             return true;
           }
         }
      }
      catch (Exception e){
        log.error(e.getMessage());
        log.info("Authentication Token could not be parsed");
      }
      return false;
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
