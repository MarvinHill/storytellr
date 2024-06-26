package de.storyteller.api.service.poll;

import de.storyteller.api.v1.dto.poll.CreatePollRequest;
import de.storyteller.api.v1.dto.poll.PollDTO;

public interface PollService {
  /**
   * Get poll by id
   *
   * @param pollId the poll id
   * @return the poll
   */
  PollDTO getPoll(String pollId);

  /**
   * Check if a poll exists by id
   *
   * @param pollId the poll id
   * @return true if the poll exists, false otherwise
   */
  boolean pollExists(String pollId);

  /**
   * Create a poll
   *
   * @param pollDTO the poll dto
   * @return the created poll
   */
  PollDTO createPoll(CreatePollRequest pollDTO);

  /**
   * Update poll options
   *
   * @param pollDTO the poll dto
   * @return the updated poll
   */
  PollDTO updatePollOptions(PollDTO pollDTO);

  /**
   * Vote for a poll option
   *
   * @param pollId       the poll id
   * @param pollOptionId the poll option id
   * @return the updated poll
   */
  PollDTO voteForPollOption(String pollId, String pollOptionId);

  /**
   * Get the vote state of the current user for a poll
   *
   * @param pollId the poll id
   * @return true if the user voted, false otherwise
   */
  boolean getVoteState(String pollId);
}
