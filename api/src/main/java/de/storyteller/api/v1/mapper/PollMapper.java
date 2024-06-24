package de.storyteller.api.v1.mapper;

import de.storyteller.api.model.Poll;
import de.storyteller.api.model.PollOption;
import de.storyteller.api.repository.PollRepository;
import de.storyteller.api.v1.auth.UserService;
import de.storyteller.api.v1.dto.poll.CreatePollRequest;
import de.storyteller.api.v1.dto.poll.PollDTO;
import de.storyteller.api.v1.dto.poll.PollOptionDTO;
import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Mapper for the entity Poll and its DTO PollDTO.
 */

@Component
@Mapper(componentModel = "spring")
public abstract class PollMapper {
  @Autowired
  protected UserService userService;
  @Autowired
  protected PollRepository pollRepository;

  @Mapping(target = "id", source = "id")
  public abstract PollDTO toPollDTO(Poll poll);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "owner", expression = "java(userService.getCurrentUser())")
  public abstract Poll createPoll(CreatePollRequest poll);

  @Mapping(target="owner", expression = "java(userService.getCurrentUser())")
  public abstract CreatePollRequest toCreateRequest(PollDTO poll);

  public abstract PollOptionDTO toPollOptionDTO(PollOption pollOption);

  @Mapping(target = "id", expression = "java(generateId())")
  @Mapping(target = "voteCount", constant = "0l")
  public abstract PollOption toPollOptionWithVoteReset(PollOptionDTO pollOption);

  public String generateId(){
    return UUID.randomUUID().toString();
  }

}
