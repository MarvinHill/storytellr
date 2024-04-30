package de.storyteller.api.service.chapter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ChapterServiceImplTest {

    @Test
    @DisplayName("isValidJSON returns true when valid JSON")
    void isValidJSON_returnsTrue_whenValidJSON() {
        String validJSON = "{\"key\":\"value\"}";
        assertTrue(ChapterServiceImpl.isValidJSON(validJSON));
    }

    @Test
    @DisplayName("isValidJSON returns false when invalid JSON")
    void isValidJSON_returnsFalse_whenInvalidJSON() {
        String invalidJSON = "{key:\"value\"}";
        assertFalse(ChapterServiceImpl.isValidJSON(invalidJSON));
    }

    @Test
    @DisplayName("isValidJSON returns false when empty string")
    void isValidJSON_returnsFalse_whenEmptyString() {
        String emptyString = "";
        assertFalse(ChapterServiceImpl.isValidJSON(emptyString));
    }

    @Test
    @DisplayName("isValidChapterType returns true when valid chapter type")
    void isValidChapterType_returnsTrue_whenValidChapterType() throws IOException {
        String validChapterType = "{\"blocks\":[{\"type\":\"header\"}]}";
        assertTrue(ChapterServiceImpl.isValidChapterType(validChapterType));
    }

    @Test
    @DisplayName("isValidChapterType returns false when invalid chapter type")
    void isValidChapterType_returnsFalse_whenInvalidChapterType() throws IOException {
        String invalidChapterType = "{\"blocks\":[{\"type\":\"invalidType\"}]}";
        assertFalse(ChapterServiceImpl.isValidChapterType(invalidChapterType));
    }

    @Test
    @DisplayName("isValidChapterType returns false when no type attribute")
    void isValidChapterType_returnsFalse_whenNoTypeAttribute() throws IOException {
        String noTypeAttribute = "{\"blocks\":[{}]}";
        assertFalse(ChapterServiceImpl.isValidChapterType(noTypeAttribute));
    }

    @Test
    @DisplayName("isValidChapterType returns false when blocks attribute is missing")
    void isValidChapterType_returnsFalse_whenBlocksAttributeMissing() throws IOException {
        String blocksAttributeMissing = "{}";
        assertFalse(ChapterServiceImpl.isValidChapterType(blocksAttributeMissing));
    }

    @Test
    @DisplayName("isValidChapterId returns true when valid id")
    void isValidChapterId_returnsTrue_whenValidId() throws IOException {
        String validIdContent = "{\"blocks\":[{\"id\":\"1234567890\"}]}";
        assertTrue(ChapterServiceImpl.isValidChapterId(validIdContent));
    }

    @Test
    @DisplayName("isValidChapterId returns false when id length not equal to 10")
    void isValidChapterId_returnsFalse_whenIdLengthNotEqualTo10() throws IOException {
        String invalidIdLengthContent = "{\"blocks\":[{\"id\":\"12345\"}]}";
        assertFalse(ChapterServiceImpl.isValidChapterId(invalidIdLengthContent));
    }

    @Test
    @DisplayName("isValidChapterId returns false when id attribute is missing")
    void isValidChapterId_returnsFalse_whenIdAttributeMissing() throws IOException {
        String idAttributeMissingContent = "{\"blocks\":[{}]}";
        assertFalse(ChapterServiceImpl.isValidChapterId(idAttributeMissingContent));
    }

    @Test
    @DisplayName("hasValidAttributes returns true when all attributes are valid")
    void hasValidAttributes_returnsTrue_whenAllAttributesAreValid() throws IOException {
        String validContent = "{\"time\":1628006400000,\"blocks\":[{\"id\":\"1234567890\",\"type\":\"header\",\"data\":{}}],\"version\":\"1.0\"}";
        assertTrue(ChapterServiceImpl.hasValidAttributes(validContent));
    }

    @Test
    @DisplayName("hasValidAttributes returns false when invalid attribute present")
    void hasValidAttributes_returnsFalse_whenInvalidAttributePresent() throws IOException {
        String invalidAttributeContent = "{\"invalidAttribute\":\"value\",\"blocks\":[{\"id\":\"1234567890\",\"type\":\"header\",\"data\":{}}]}";
        assertFalse(ChapterServiceImpl.hasValidAttributes(invalidAttributeContent));
    }

    @Test
    @DisplayName("hasValidAttributes returns false when blocks is not an array")
    void hasValidAttributes_returnsFalse_whenBlocksIsNotAnArray() throws IOException {
        String blocksNotArrayContent = "{\"blocks\":{\"id\":\"1234567890\",\"type\":\"header\",\"data\":{}}}";
        assertFalse(ChapterServiceImpl.hasValidAttributes(blocksNotArrayContent));
    }

    @Test
    @DisplayName("hasValidAttributes returns false when block attribute is missing")
    void hasValidAttributes_returnsFalse_whenBlockAttributeMissing() throws IOException {
        String blockAttributeMissingContent = "{\"blocks\":[{\"id\":\"1234567890\",\"type\":\"header\"}]}";
        assertFalse(ChapterServiceImpl.hasValidAttributes(blockAttributeMissingContent));
    }

    @Test
    @DisplayName("hasValidAttributes returns false when blocks attribute is missing")
    void hasValidAttributes_returnsFalse_whenBlocksAttributeMissing() throws IOException {
        String blocksAttributeMissingContent = "{\"time\":1628006400000,\"version\":\"1.0\"}";
        assertFalse(ChapterServiceImpl.hasValidAttributes(blocksAttributeMissingContent));
    }
}