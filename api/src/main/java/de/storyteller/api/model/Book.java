package de.storyteller.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.UUID;


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

    @OneToMany
    private List<String> tags;
    private String cover;






}
