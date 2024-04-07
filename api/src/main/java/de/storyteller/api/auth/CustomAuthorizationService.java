package de.storyteller.api.auth;

import de.storyteller.api.dto.book.BookDTO;
import de.storyteller.api.service.book.BookService;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service("sAuthService")
@Slf4j
public class CustomAuthorizationService {
    Authentication authentication;

    BookService bookService;

    public boolean userOwnsBook(UUID bookId){
      //BookDTO bookDTO = bookService.getBookById(bookId);
      //if (bookDTO == null) return false;
      //if(!(authentication.getPrincipal() instanceof UserDetails)) return false;
      //if (bookDTO.getAuthor().equals(authentication.getPrincipal()))

      log.debug("User Details Auth", this.authentication.getDetails());
      return true;
    }

    public boolean testLog(){
      log.debug("User Details Auth", this.authentication.getDetails());
      return true;
    }
}
