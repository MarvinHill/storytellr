package de.storyteller.api.service.poll;

import de.storyteller.api.v1.dto.poll.CreatePollRequest;
import de.storyteller.api.v1.dto.poll.PollDTO;

public interface PollService {

  PollDTO getPoll(String pollId);
  boolean pollExists(String pollId);
  PollDTO createPoll(CreatePollRequest pollDTO);

  PollDTO updatePollOptions(PollDTO pollDTO);
}
