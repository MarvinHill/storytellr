package de.storyteller.api.service.poll;

import de.storyteller.api.v1.dto.poll.CreatePollOptionRequest;
import de.storyteller.api.v1.dto.poll.CreatePollRequest;
import de.storyteller.api.v1.dto.poll.DeletePollOptionRequest;
import de.storyteller.api.v1.dto.poll.DeletePollRequest;
import de.storyteller.api.v1.dto.poll.PollDTO;
import de.storyteller.api.v1.dto.poll.UpdatePollOptionRequest;
import de.storyteller.api.v1.dto.poll.UpdatePollRequest;

public interface PollService {
  PollDTO createPoll(CreatePollRequest createRequest);

  PollDTO updatePoll(UpdatePollRequest updateRequest);

  void deletePoll(DeletePollRequest deletePollRequest);

  PollDTO createPollOption(CreatePollOptionRequest createPollOptionRequest);

  PollDTO deletePollOption(DeletePollOptionRequest deleteRequest);

  PollDTO updatePollOption(UpdatePollOptionRequest updateRequest);

  PollDTO getPoll(String pollId);
}
