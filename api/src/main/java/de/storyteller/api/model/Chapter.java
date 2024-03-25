package de.storyteller.api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;
import org.springframework.boot.jackson.JsonComponent;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Chapter {
    @Id
    private Long id;
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
