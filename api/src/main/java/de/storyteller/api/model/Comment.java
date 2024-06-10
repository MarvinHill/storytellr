package de.storyteller.api.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    public String id;
    public String content;
    public String author;
    public String chapterId;
    public String blockId;
}
