package de.storyteller.api.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Document(collection = "chapters")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chapter {
    @Id
    private String id;

    @DBRef
    private Book book;
    private String chapterTitle;

    private String content;

    private LocalDateTime lastModified;

    public JSONObject getContentAsJSONObject() {
        return new JSONObject(content);
    }

    public void setContentFromJSONObject(JSONObject jsonObject) {
        this.content = jsonObject.toString();
    }



}
