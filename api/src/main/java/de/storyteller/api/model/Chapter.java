package de.storyteller.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;
import org.springframework.boot.jackson.JsonComponent;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    private String chapterTitle;
    @Column(columnDefinition = "json")
    private String content;

    private LocalDateTime lastModified;

    public JSONObject getContentAsJSONObject() {
        return new JSONObject(content);
    }

    public void setContentFromJSONObject(JSONObject jsonObject) {
        this.content = jsonObject.toString();
    }



}
