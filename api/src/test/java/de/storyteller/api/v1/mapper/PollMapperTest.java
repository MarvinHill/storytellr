package de.storyteller.api.v1.mapper;

import de.storyteller.api.model.Poll;
import de.storyteller.api.model.PollOption;
import de.storyteller.api.repository.PollRepository;
import de.storyteller.api.v1.auth.UserService;
import de.storyteller.api.v1.dto.poll.CreatePollRequest;
import de.storyteller.api.v1.dto.poll.PollDTO;
import de.storyteller.api.v1.dto.poll.PollOptionDTO;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class PollMapperTest {

  @InjectMocks
  private PollMapperImpl pollMapper;

  @Mock
  UserService userService;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  @DisplayName("Should map to PollDTO")
  public void shouldMapToPollDTO() {
    when(userService.getCurrentUser()).thenReturn("b7c28614-c993-459b-991a-f080405ca76c");
    String someId = "2351";
    String someQuestion = "Test question";
    List<PollOption> someOptions = List.of(
        new PollOption("1", "Option 1", 0),
        new PollOption("2", "Option 2", 0)
    );
    List<PollOptionDTO> expectedDTOOptions = List.of(
        new PollOptionDTO("1", "Option 1", 0),
        new PollOptionDTO("2", "Option 2", 0)
    );

    Poll poll = new Poll(
        someId,
        userService.getCurrentUser(),
        someQuestion,
        someOptions
    );

    PollDTO pollDTO = pollMapper.toPollDTO(poll);

    assertEquals(someId, pollDTO.getId());
    assertEquals(someQuestion, pollDTO.getQuestion());
    assertNotNull(pollDTO.getOptions());
    assertEquals(someOptions.size(), pollDTO.getOptions().size());
    assertEquals(expectedDTOOptions, pollDTO.getOptions());
  }

  @Test
  @DisplayName("Should map to Create Request")
  public void shouldMapToCreateRequest() {
    PollDTO pollDTO = new PollDTO();
    pollDTO.setId("123");
    pollDTO.setQuestion("Test question");

    CreatePollRequest createPollRequest = pollMapper.toCreateRequest(pollDTO);

    assertEquals(userService.getCurrentUser(), createPollRequest.getOwner());
    assertEquals("Test question", createPollRequest.getQuestion());
  }

  @Test
  @DisplayName("Should generate a id")
  public void shouldGenerateId() {
    String id = pollMapper.generateId();

    assertNotNull(id);
  }

  @Test
  @DisplayName("Should map to a PollOptionDTO")
  public void shouldMapToPollOptionDTO() {
    PollOption pollOption = new PollOption();
    pollOption.setId("123");
    pollOption.setContent("Test option");

    PollOptionDTO pollOptionDTO = pollMapper.toPollOptionDTO(pollOption);

    assertEquals("123", pollOptionDTO.getId());
    assertEquals("Test option", pollOptionDTO.getContent());
  }

  @Test
  @DisplayName("Should map to a PollOption with a vote reset")
  public void shouldMapToPollOptionWithVoteReset() {
    PollOptionDTO pollOptionDTO = new PollOptionDTO("123", "Test option", 123);

    PollOption pollOption = pollMapper.toPollOptionWithVoteReset(pollOptionDTO);

    // Check if id is generated
    assertNotEquals("123", pollOption.getId());
    assertEquals("Test option", pollOption.getContent());
    //Check if vote count is reset to 0
    assertEquals(0, pollOption.getVoteCount());
  }
}