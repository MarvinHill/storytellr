package de.storyteller.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Model class to save the progress of a user in a book
 */
@Document(collection = "library")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Progress {
    @Id
    private String id;
    private String user;
    private String bookId;
    private int readChapters = 0;
}
