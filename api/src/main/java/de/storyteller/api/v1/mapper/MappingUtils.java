package de.storyteller.api.v1.mapper;

import de.storyteller.api.model.Poll;
import de.storyteller.api.model.PollOption;
import de.storyteller.api.repository.PollRepository;
import de.storyteller.api.v1.dto.poll.UpdatePollOptionRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MappingUtils {

  private final PollRepository pollRepository;

  public PollOption getPollOption(UpdatePollOptionRequest updatePollOptionRequest){

    Poll poll = pollRepository.findById(updatePollOptionRequest.getPollId()).orElseThrow();
    List<PollOption> pollOptions = poll.getOptions();

    for(PollOption pollOption : pollOptions){
      if(pollOption.getPollOptionId().equals(updatePollOptionRequest.getPollOptionId())){
        return pollOption;
      }
    }

    throw new RuntimeException("PollOption with id: " + updatePollOptionRequest.getPollOptionId() + " doesn't exist");
  }
}
