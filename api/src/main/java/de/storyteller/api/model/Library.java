package de.storyteller.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "library")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Library {
    private String userId;
    private List<Book> books;
}
