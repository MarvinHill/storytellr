package de.storyteller.api.model;

import de.storyteller.api.converter.CoverUriConverterJson;
import de.storyteller.api.dto.cover.CoverUriDTO;
import jakarta.persistence.*;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import lombok.Setter;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String title;

    @ManyToOne
    private Genre genre;

    private String author;

    @Column(length=200)
    private String description;

    @Column(length=75)
    private String catchphrase;

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "book_tags", joinColumns = @JoinColumn(name = "book_id"))
    private List<String> tags = new ArrayList<>();

    @Convert(converter = CoverUriConverterJson.class)
    private CoverUriDTO cover;
}
