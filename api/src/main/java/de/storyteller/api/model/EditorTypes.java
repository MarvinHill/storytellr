package de.storyteller.api.model;

/**
 * Enum for the different types blocks for the editor
 */
public enum EditorTypes {
    HEADER("header"),
    PARAGRAPH("paragraph"),
    POLL("poll");

    private final String type;

    EditorTypes(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
