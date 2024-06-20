package de.storyteller.api.service.poll;

import de.storyteller.api.model.Poll;
import de.storyteller.api.model.PollOption;
import de.storyteller.api.repository.BookRepository;
import de.storyteller.api.repository.PollRepository;
import de.storyteller.api.v1.auth.UserService;
import de.storyteller.api.v1.auth.CustomAuthorizationService;
import de.storyteller.api.v1.dto.poll.CreatePollOptionRequest;
import de.storyteller.api.v1.dto.poll.CreatePollRequest;
import de.storyteller.api.v1.dto.poll.DeletePollOptionRequest;
import de.storyteller.api.v1.dto.poll.DeletePollRequest;
import de.storyteller.api.v1.dto.poll.PollDTO;
import de.storyteller.api.v1.dto.poll.PollOptionDTO;
import de.storyteller.api.v1.dto.poll.UpdatePollOptionRequest;
import de.storyteller.api.v1.dto.poll.UpdatePollRequest;
import de.storyteller.api.v1.mapper.PollMapper;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PollServiceImpl implements PollService {

  private final UserService userService;
  private final BookRepository bookRepository;
  private final PollRepository pollRepository;
  private final PollMapper pollMapper;
  private final CustomAuthorizationService customAuthorizationService;

  @Override
  public PollDTO createPoll(CreatePollRequest createRequest) {
    if(!bookRepository.existsById(createRequest.getBookId())){
      throw new RuntimeException("Book with id: " + createRequest.getBookId() + " doesn't exist");
    }
    Poll poll = pollMapper.toPoll(createRequest);
    return pollMapper.toPollDTO(pollRepository.save(poll));
  }

  @Override
  public PollDTO updatePoll(UpdatePollRequest updateRequest) {
    if(!pollRepository.existsById(updateRequest.getPollId())){
      throw new RuntimeException("Poll with id: " + updateRequest.getPollId() + " doesn't exist");
    }
    Poll poll = pollMapper.toPoll(updateRequest);
    return pollMapper.toPollDTO(pollRepository.save(poll));
  }

  @Override
  public void deletePoll(DeletePollRequest deletePollRequest) {
    if(!pollRepository.existsById(deletePollRequest.getPollId())){
      throw new RuntimeException("Poll with id: " + deletePollRequest.getPollId() + " doesn't exist");
    }
    pollRepository.deleteById(deletePollRequest.getPollId());
  }

  @Override
  public PollDTO createPollOption(CreatePollOptionRequest createPollOptionRequest) {
    if(!pollRepository.existsById(createPollOptionRequest.getPollId())){
      throw new RuntimeException("Poll with id: " + createPollOptionRequest.getPollId() + " doesn't exist");
    }
    Poll poll = pollRepository.findById(createPollOptionRequest.getPollId()).orElseThrow();
    PollOption option = pollMapper.toPollOption(createPollOptionRequest);
    poll.getOptions().add(option);
    return pollMapper.toPollDTO(pollRepository.save(poll));
  }

  @Override
  public PollDTO deletePollOption(DeletePollOptionRequest deleteRequest) {
    if(!pollRepository.existsById(deleteRequest.getPollId())){
      throw new RuntimeException("Poll with id: " + deleteRequest.getPollId() + " doesn't exist");
    }
    Poll poll = pollRepository.findById(deleteRequest.getPollId()).orElseThrow();
    poll.getOptions().removeIf(option -> option.getPollOptionId().equals(deleteRequest.getOptionId()));
    return pollMapper.toPollDTO(pollRepository.save(poll));
  }

  @Override
  public PollDTO updatePollOption(UpdatePollOptionRequest updateRequest) {
    if(!pollRepository.existsById(updateRequest.getPollId())){
      throw new RuntimeException("Poll with id: " + updateRequest.getPollId() + " doesn't exist");
    }
    Poll poll = pollRepository.findById(updateRequest.getPollId()).orElseThrow();
    List<PollOption> optionList = poll.getOptions().stream().map(option -> {
      if(option.getPollOptionId().equals(updateRequest.getPollOptionId())){
        return pollMapper.toPollOption(updateRequest);
      }
      return option;
    }).collect(Collectors.toList());
    poll.setOptions(optionList);
    return pollMapper.toPollDTO(pollRepository.save(poll));
  }

  @Override
  public PollDTO getPoll(String pollId) {
    if(!pollRepository.existsById(pollId)){
      throw new RuntimeException("Poll with id: " + pollId + " doesn't exist");
    }
    return pollRepository.findById(pollId).map(pollMapper::toPollDTO).orElseThrow();
  }
}
