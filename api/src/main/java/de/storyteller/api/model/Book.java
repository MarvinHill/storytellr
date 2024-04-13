package de.storyteller.api.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;


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
    private String cover;



}
