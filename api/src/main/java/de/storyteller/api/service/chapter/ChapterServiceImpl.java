package de.storyteller.api.service.chapter;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import de.storyteller.api.model.EditorTypes;
import de.storyteller.api.repository.PollRepository;
import de.storyteller.api.service.poll.PollServiceImpl;
import de.storyteller.api.v1.auth.CustomAuthorizationService;
import de.storyteller.api.v1.auth.UserService;
import de.storyteller.api.v1.dto.chapter.AddChapterRequest;
import de.storyteller.api.v1.dto.chapter.ChapterDTO;
import de.storyteller.api.v1.dto.chapter.EditChapterRequest;
import de.storyteller.api.v1.dto.poll.PollDTO;
import de.storyteller.api.v1.dto.poll.PollOptionDTO;
import de.storyteller.api.v1.mapper.ChapterMapper;
import de.storyteller.api.model.Chapter;
import de.storyteller.api.repository.BookRepository;
import de.storyteller.api.repository.ChapterRepository;
import de.storyteller.api.model.Book;

import de.storyteller.api.v1.mapper.PollMapper;
import java.io.IOException;
import java.util.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChapterServiceImpl implements ChapterService {
    protected final ChapterRepository chapterRepository;
    protected final BookRepository bookRepository;
    protected final ChapterMapper chapterMapper;
    protected final PollServiceImpl pollService;
    protected final PollMapper pollMapper;
    protected final UserService userService;

    static ObjectMapper objectMapper = new ObjectMapper();
    protected final PollRepository pollRepository;

    @Override
    public ChapterDTO createChapter(AddChapterRequest chapter) {
        if (!bookRepository.existsById(chapter.getBookId())) {
            throw new RuntimeException("Book with id: " + chapter.getBookId() + " doesn't exist");
        }
        Book book = bookRepository.findById(chapter.getBookId()).get();
        ChapterDTO savedChapter = chapterMapper.toChapterDTO(chapterRepository.save(chapterMapper.toChapter(chapter)));
        book.getChapters().add(chapterMapper.toChapter(savedChapter));
        bookRepository.save(book);
        return savedChapter;
    }

    @Override
    public Optional<ChapterDTO> getChapterById(String chapterId) {
        Optional<Chapter> chapterOptional = chapterRepository.findById(chapterId);
        return chapterOptional.isPresent() ? Optional.of(chapterMapper.toChapterDTO(chapterOptional.get())) : Optional.empty();
    }

    @Override
    public ChapterDTO updateChapter(EditChapterRequest chapter) {
        if (!chapterRepository.existsById(chapter.getId())) {
            throw new RuntimeException("Chapter with id: " + chapter.getId() + " doesn't exist");
        }
        try {
            boolean valid = isValidChapterContent(chapter.getContent());
            if (!valid) {
                throw new RuntimeException("Chapter content is not valid");
            }
        } catch (IOException e) {
            throw new RuntimeException("Chapter content is not valid JSON");
        }
        chapter.setContent(processBlocks(chapter.getContent()));


        return chapterMapper.toChapterDTO(chapterRepository.save(chapterMapper.toChapter(chapter)));
    }

    protected String processBlocks(final String chapterContent) {
        try {
            JsonNode jsonNode = objectMapper.readTree(chapterContent);
            log.info("node: " + jsonNode);
            JsonNode blocksNode = jsonNode.path("blocks");
            if (blocksNode.isMissingNode()) {
                log.error("No 'blocks' attribute found in chapter content");
                throw new RuntimeException("Chapter content is not valid JSON");
            }

            // Convert blocksNode to List<JsonNode>
            List<JsonNode> blocks = new ArrayList<>();
            for (JsonNode block : blocksNode) {
                blocks.add(block);
            }

            // Replace the block
            for (int j = 0; j < blocks.size(); j++) {
                JsonNode block = blocks.get(j);
                if (block.has("type")) {
                    // Check if the value of the "type" attribute is a valid enum value
                    String chapterTypeString = block.get("type").asText();

                    if(chapterTypeString.equals(EditorTypes.POLL.getType())){
                        createOrUpdatePoll(block);
                    }
                }
            }

            // Convert List<JsonNode> back to JsonNode
            JsonNode newBlocksNode = objectMapper.valueToTree(blocks);

            // Replace blocksNode in jsonNode
            ((ObjectNode) jsonNode).set("blocks", newBlocksNode);

          return objectMapper.writeValueAsString(jsonNode);

        }
        catch (IOException e) {
            throw new RuntimeException("Chapter content is not valid JSON");
        }

    }


    protected void createOrUpdatePoll(JsonNode block){
        try {
            JsonNode pollNode = block.get("data");

            PollDTO pollDTO = new ObjectMapper().treeToValue(pollNode, PollDTO.class);


            if(pollDTO.getId() == null){
                throw new RuntimeException("Poll data is missing id");
            }
            String id = pollDTO.getId();

            if(pollService.pollExists(id) && pollRepository.findById(id).orElseThrow().getOwner().equals(userService.getCurrentUser())){
                pollDTO = pollService.updatePollOptions(pollDTO);
                ((ObjectNode)block).set("data", objectMapper.valueToTree(pollDTO));
                return;
            }

            PollDTO createdPollDto = pollService.createPoll(pollMapper.toCreateRequest(pollDTO));
            ((ObjectNode)block).set("data", objectMapper.valueToTree(createdPollDto));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error creating poll");
        }
    }

    @Override
    public List<ChapterDTO> getAllChapters() {
        List<Chapter> chapters = chapterRepository.findAll();
        return chapters.stream()
                .map(chapterMapper::toChapterDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ChapterDTO> getChaptersByBookId(String bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        if(book.isEmpty()){
            throw new RuntimeException("Book with id: " + bookId + " doesn't exist");
        }
        Book book1 = book.get();
        List<Chapter> chapterIds = book1.getChapters();
        return chapterIds.stream()
                .map(chapterMapper::toChapterDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ChapterDTO> getPublishedChaptersByBookId(String bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isEmpty()) {
            throw new RuntimeException("Book with id: " + bookId + " doesn't exist");
        }
        Book book1 = book.get();
        List<Chapter> chapters = book1.getChapters();

        // Filter published chapters
        List<Chapter> publishedChapters = chapters.stream()
                .filter(Chapter::isPublished)
                .toList();

        return publishedChapters.stream()
                .map(chapterMapper::toChapterDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ChapterDTO> getFirstNPublishedChapters(String bookId, int count) {
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isEmpty()) {
            throw new RuntimeException("Book with id: " + bookId + " doesn't exist");
        }
        Book book1 = book.get();
        List<Chapter> chapters = book1.getChapters();

        // Filter published chapters
        List<Chapter> publishedChapters = chapters.stream()
                .filter(Chapter::isPublished)
                .toList();

        // Return the first N published chapters
        return publishedChapters.stream()
                .limit(count)
                .map(chapterMapper::toChapterDTO)
                .collect(Collectors.toList());
    }

    public static boolean isValidJSON(String chapterContent) {
        boolean valid = true;
        if (chapterContent == null || chapterContent.isEmpty()) {
            return false;
        }
        try {
            objectMapper.readTree(chapterContent);
        } catch (JsonProcessingException e) {
            valid = false;
        }
        return valid;
    }

    public static boolean isValidChapterContent(String chapterContent) throws IOException {

        if (!isValidJSON(chapterContent)) {
            return false;
        } else if (!hasValidAttributes(chapterContent)) {
            return false;
        } else if (!isValidChapterType(chapterContent)) {
            return false;
        }
        return isValidChapterId(chapterContent);
    }

    public static boolean isValidChapterType(final String chapterContent) throws IOException {
        boolean valid = true;
        JsonNode jsonNode = objectMapper.readTree(chapterContent);
        JsonNode blocksNode = jsonNode.get("blocks");
        if (blocksNode == null) {
            return false;
        }
        for (JsonNode block : blocksNode) {
            if (!block.has("type")) {
                valid = false;
            } else {
                // Check if the value of the "type" attribute is a valid enum value
                String chapterTypeString = block.get("type").asText();
                valid = Arrays.stream(EditorTypes.values())
                        .anyMatch(editorType -> editorType.getType().equals(chapterTypeString));

            }
        }

        return valid;
    }

    public static boolean isValidChapterId(final String chapterContent) throws IOException {
        boolean valid = true;
        JsonNode jsonNode = objectMapper.readTree(chapterContent);
        JsonNode blocksNode = jsonNode.get("blocks");
        for (JsonNode block : blocksNode) {
            if (!block.has("id")) {
                valid = false;
            } else {
                String id = block.get("id").asText();
                if (id.length() != 10) {
                    valid = false;
                }
            }
        }
        return valid;
    }

    public static boolean hasValidAttributes(final String chapterContent) throws IOException {
        JsonNode jsonNode = objectMapper.readTree(chapterContent);

        // List of allowed attributes
        Set<String> allowedAttributes = new HashSet<>();
        allowedAttributes.add("time");
        allowedAttributes.add("blocks");
        allowedAttributes.add("version");
        allowedAttributes.add("id");
        allowedAttributes.add("type");
        allowedAttributes.add("data");

        // Check if all attributes are allowed
        for (Iterator<String> it = jsonNode.fieldNames(); it.hasNext(); ) {
            String attributeName = it.next();
            if (!allowedAttributes.contains(attributeName)) {
                // Invalid if an attribute is not allowed
                return false;
            }
        }

        // Check attributes of blocks
        if (jsonNode.has("blocks")) {
            JsonNode blocksNode = jsonNode.get("blocks");
            if (!blocksNode.isArray()) {
                // If "blocks" is not an array, the structure is invalid
                return false;
            } else {
                for (JsonNode block : blocksNode) {
                    if (!block.has("id") || !block.has("type") || !block.has("data")) {
                        // If a block is missing an attribute, the structure is invalid
                        return false;
                    }
                }
            }
        } else {
            return false;
        }

        return true;
    }
}
