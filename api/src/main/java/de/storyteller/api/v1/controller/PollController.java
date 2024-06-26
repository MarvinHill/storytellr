package de.storyteller.api.v1.controller;

import de.storyteller.api.service.poll.PollService;
import de.storyteller.api.v1.dto.poll.PollDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/poll")
public class PollController {

  private final PollService pollService;

  /**
   * Vote for a poll option
   * @param pollId The id of the poll
   * @param pollOptionId The id of the poll option
   * @return A poll with the updated vote count
   */
  @PreAuthorize("isAuthenticated()")
  @PostMapping("/{pollId}/vote/{pollOptionId}")
  public ResponseEntity<PollDTO> voteForPollOption(@PathVariable String pollId, @PathVariable String pollOptionId){

    PollDTO pollDTO = pollService.voteForPollOption(pollId, pollOptionId);

    return ResponseEntity.ok(pollDTO);
  }

  /**
   * Get the vote state of a poll
   * @param pollId The id of the poll
   * @return True if the user has already voted, false otherwise
   */
  @PreAuthorize("isAuthenticated()")
  @GetMapping("/{pollId}/voteState")
  public ResponseEntity<Boolean> getVoteState(@PathVariable String pollId){

    boolean voteState = pollService.getVoteState(pollId);

    return ResponseEntity.ok(voteState);
  }

  /**
   * Get a poll by its id
   * @param pollId The id of the poll
   * @return The poll with the given id
   */
  @PreAuthorize("permitAll()")
  @GetMapping("/{pollId}")
  public ResponseEntity<PollDTO> getPoll(@PathVariable String pollId){

    PollDTO pollDTO = pollService.getPoll(pollId);

    return ResponseEntity.ok(pollDTO);
  }

}
