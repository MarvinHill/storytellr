package de.storyteller.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "library")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Library {
    @Id
    private String id;
    private String ownerId;
    private List<Book> books = new ArrayList<>();
    private List<Book> likedBooks = new ArrayList<>();
}
