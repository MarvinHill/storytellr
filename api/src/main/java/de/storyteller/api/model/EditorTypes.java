package de.storyteller.api.model;

public enum EditorTypes {
    HEADER("header"),
    PARAGRAPH("paragraph");

    private final String type;

    EditorTypes(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
