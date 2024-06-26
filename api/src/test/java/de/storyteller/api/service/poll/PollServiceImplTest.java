package de.storyteller.api.service.poll;

import static org.junit.jupiter.api.Assertions.*;
import de.storyteller.api.model.Poll;
import de.storyteller.api.model.PollOption;
import de.storyteller.api.repository.PollRepository;
import de.storyteller.api.v1.dto.poll.CreatePollRequest;
import de.storyteller.api.v1.dto.poll.PollDTO;
import de.storyteller.api.v1.dto.poll.PollOptionDTO;
import de.storyteller.api.v1.mapper.PollMapper;
import jakarta.validation.Valid;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Slf4j
class PollServiceImplTest {

  @Mock
  private PollRepository pollRepository;

  @Mock
  private PollMapper pollMapper;

  @InjectMocks
  private PollServiceImpl pollService;

  private PollDTO pollDTO;
  private Poll poll;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    List<PollOption> someOptions = List.of(
        new PollOption("1", "Option 1", 0),
        new PollOption("2", "Option 2", 0)
    );
    List<PollOptionDTO> expectedDTOOptions = List.of(
        new PollOptionDTO("1", "Option 1", 0),
        new PollOptionDTO("2", "Option 2", 0)
    );

    pollDTO = new PollDTO();
    pollDTO.setOptions(expectedDTOOptions);
    pollDTO.setId("testPollId");
    poll = new Poll();
    poll.setOptions(someOptions);
  }

  @Test
  void getPoll_whenPollExists_returnsPollDTO() {
    String pollId = "testPollId";

    when(pollRepository.existsById(pollId)).thenReturn(true);
    when(pollRepository.findById(pollId)).thenReturn(Optional.of(poll));
    when(pollMapper.toPollDTO(poll)).thenReturn(pollDTO);

    PollDTO result = pollService.getPoll(pollId);

    assertEquals(pollDTO, result);
  }

  @Test
  void getPoll_whenPollDoesNotExist_throwsException() {
    String pollId = "testPollId";

    when(pollRepository.existsById(pollId)).thenReturn(false);

    assertThrows(RuntimeException.class, () -> pollService.getPoll(pollId));
  }

  @Test
  void createPoll_createsAndReturnsPollDTO() {
    CreatePollRequest createPollRequest = new CreatePollRequest();

    when(pollMapper.createPoll(createPollRequest)).thenReturn(poll);
    when(pollRepository.save(poll)).thenReturn(poll);
    when(pollMapper.toPollDTO(poll)).thenReturn(pollDTO);

    PollDTO result = pollService.createPoll(createPollRequest);

    assertEquals(pollDTO, result);
  }

  @Test
  void updatePollOptions_whenPollExists_updatesAndReturnsPollDTO() {

    when(pollRepository.existsById(pollDTO.getId())).thenReturn(true);
    when(pollRepository.findById(pollDTO.getId())).thenReturn(Optional.of(poll));
    when(pollRepository.save(poll)).thenReturn(poll);
    when(pollMapper.toPollDTO(poll)).thenReturn(pollDTO);

    PollDTO result = pollService.updatePollOptions(pollDTO);

    log.info("PollDTO: " + pollDTO.getOptions());
    log.info("Result: " + result.getOptions());
    assertEquals(pollDTO.getOptions(), result.getOptions());
  }

  @Test
  void updatePollOptions_whenPollDoesNotExist_throwsException() {

    when(pollRepository.existsById(pollDTO.getId())).thenReturn(false);

    assertThrows(RuntimeException.class, () -> pollService.updatePollOptions(pollDTO));
  }
}