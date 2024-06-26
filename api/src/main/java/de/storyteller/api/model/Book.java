package de.storyteller.api.model;

import de.storyteller.api.v1.dto.cover.CoverUriDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Model class for a book
 */
@Document(collection = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    private String id;

    private String title;

    @DBRef
    private Genre genre;
    private String author;
    private String description;
    private String catchphrase;
    @DBRef
    private List<Chapter> chapters = new ArrayList<>();
    private List<String> tags = new ArrayList<>();
    private CoverUriDTO cover;
    private boolean isPublic;
    private boolean adultContent;
    private boolean commentsDeactivated;
    private boolean finished;
    private int likes;
}
