package de.storyteller.api.service.poll;

import de.storyteller.api.model.Poll;
import de.storyteller.api.model.PollOption;
import de.storyteller.api.model.PollOptionVote;
import de.storyteller.api.repository.PollOptionVoteRepository;
import de.storyteller.api.repository.PollRepository;
import de.storyteller.api.service.chapter.ChapterServiceImpl;
import de.storyteller.api.v1.auth.UserService;
import de.storyteller.api.v1.dto.poll.CreatePollRequest;
import de.storyteller.api.v1.dto.poll.PollDTO;
import de.storyteller.api.v1.mapper.PollMapper;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PollServiceImpl implements PollService {

  private final PollRepository pollRepository;
  private final PollMapper pollMapper;
  private final PollOptionVoteRepository pollOptionVoteRepository;
  private final UserService userService;

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
    if(pollDTO.getQuestion() != null){
      poll.setQuestion(pollDTO.getQuestion());
    }
    if(pollDTO.getOptions() != null){
      poll.setOptions(pollDTO.getOptions().stream().map(pollMapper::toPollOptionWithVoteReset).collect(Collectors.toList()));
    }
    return pollMapper.toPollDTO(pollRepository.save(poll));
  }

  @Override
  public PollDTO voteForPollOption(String pollId, String pollOptionId) {

    if(!pollRepository.existsById(pollId)){
      throw new RuntimeException("Poll with id: " + pollId + " doesn't exist");
    }

    String currentUser = userService.getCurrentUser();

    Poll poll = pollRepository.findById(pollId).get();

    //Check if pollOptionId exists in the poll
    poll.getOptions().stream().filter(pollOption -> pollOption.getId().equals(pollOptionId)).findFirst().orElseThrow();


    Optional<PollOptionVote> pollOptionVoteOptional = pollOptionVoteRepository.findByPollIdAndUserId(pollId, currentUser);

    //Check if pollOptionVote exists
    if(pollOptionVoteOptional.isPresent()){
      //Reduce Vote Count
      poll.getOptions().forEach(pollOption -> {
        if(pollOption.getId().equals(pollOptionVoteOptional.get().getPollOptionId())){
          if(pollOption.getVoteCount() == 0){
            throw new RuntimeException("PollOption with id: " + pollOptionVoteOptional.get().getPollOptionId() + " has no votes");
          }
          pollOption.setVoteCount(pollOption.getVoteCount() - 1);
          return ;
        }
      });

      PollOptionVote pollOptionVote = pollOptionVoteOptional.get();
      pollOptionVote.setPollOptionId(pollOptionId);
      pollOptionVoteRepository.save(pollOptionVote);
    }
    else {
      PollOptionVote pollOptionVote = new PollOptionVote();
      pollOptionVote.setPollId(pollId);
      pollOptionVote.setPollOptionId(pollOptionId);
      pollOptionVote.setUserId(currentUser);
      pollOptionVoteRepository.save(pollOptionVote);
    }

    poll.getOptions().stream().filter(pollOption -> {
      if(pollOption.getId().equals(pollOptionId)){
        pollOption.setVoteCount(pollOption.getVoteCount() + 1);
        return true;
      }
      return false;
    }).findFirst().orElseThrow();

    return pollMapper.toPollDTO(pollRepository.save(poll));
  }

  @Override
  public boolean getVoteState(String pollId) {
    if(!pollRepository.existsById(pollId)){
      throw new RuntimeException("Poll with id: " + pollId + " doesn't exist");
    }
    return pollOptionVoteRepository.findByPollIdAndUserId(pollId, userService.getCurrentUser()).isPresent();
  }

}
