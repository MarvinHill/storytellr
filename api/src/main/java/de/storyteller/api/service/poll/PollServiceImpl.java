package de.storyteller.api.service.poll;

import de.storyteller.api.model.Poll;
import de.storyteller.api.repository.PollRepository;
import de.storyteller.api.v1.auth.CustomAuthorizationService;
import de.storyteller.api.v1.dto.poll.CreatePollRequest;
import de.storyteller.api.v1.dto.poll.PollDTO;
import de.storyteller.api.v1.mapper.PollMapper;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PollServiceImpl implements PollService {

  private final PollRepository pollRepository;
  private final PollMapper pollMapper;

  @Override
  public PollDTO getPoll(String pollId) {
    if(!pollRepository.existsById(pollId)){
      throw new RuntimeException("Poll with id: " + pollId + " doesn't exist");
    }
    return pollRepository.findById(pollId).map(pollMapper::toPollDTO).orElseThrow();
  }

  @Override
  public boolean pollExists(String pollId) {
    return pollRepository.existsById(pollId);
  }

  @Override
  public PollDTO createPoll(CreatePollRequest pollDTO) {
    return pollMapper.toPollDTO(pollRepository.save(pollMapper.createPoll(pollDTO)));
  }

  @Override
  public PollDTO updatePollOptions(PollDTO pollDTO) {
    if (!pollRepository.existsById(pollDTO.getId())) {
      throw new RuntimeException("Poll with id: " + pollDTO.getId() + " doesn't exist");
    }

    Poll poll = pollRepository.findById(pollDTO.getId()).orElseThrow();
    if(pollDTO.getOptions() != null){
      poll.setOptions(pollDTO.getOptions().stream().map(pollMapper::toPollOptionWithVoteReset).collect(Collectors.toList()));

    }
    return pollMapper.toPollDTO(pollRepository.save(poll));
  }
}
