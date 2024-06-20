package de.storyteller.api.v1.mapper;

import de.storyteller.api.model.Poll;
import de.storyteller.api.model.PollOption;
import de.storyteller.api.repository.PollRepository;
import de.storyteller.api.v1.auth.UserService;
import de.storyteller.api.v1.dto.poll.CreatePollOptionRequest;
import de.storyteller.api.v1.dto.poll.CreatePollRequest;
import de.storyteller.api.v1.dto.poll.PollDTO;
import de.storyteller.api.v1.dto.poll.PollOptionDTO;
import de.storyteller.api.v1.dto.poll.UpdatePollOptionRequest;
import de.storyteller.api.v1.dto.poll.UpdatePollRequest;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

/**
 * Mapper for the entity Poll and its DTO PollDTO.
 */
@RequiredArgsConstructor
@Component
@Mapper(componentModel = "spring")
public abstract class PollMapper {

  private final UserService userService;
  private final PollRepository pollRepository;


  @Mapping(target = "id", ignore = true)
  @Mapping(target = "owner", expression = "java(userService.getUserId())")
  @Mapping(target = "question", constant = "CHANGE ME")
  @Mapping(target = "options", ignore = true)
  public abstract Poll toPoll(CreatePollRequest createRequest);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "owner", expression = "java(pollRepository.findById(updateRequest.getPollId()).orElseThrow().getOwner())")
  @Mapping(target = "bookId", expression = "java(pollRepository.findById(updateRequest.getPollId()).orElseThrow().getBookId())")
  @Mapping(target = "options", expression = "java(pollRepository.findById(updateRequest.getPollId()).orElseThrow().getOptions())")
  public abstract Poll toPoll(UpdatePollRequest updateRequest);

  @Mapping(target = "id", ignore = true)
  public abstract PollDTO toPollDTO(Poll poll);

  public abstract PollOptionDTO toPollOptionDTO(PollOption pollOption);

  @Mapping(target = "pollOptionId", ignore = true)
  public abstract PollOption toPollOption(CreatePollOptionRequest createPollOptionRequest);

  public abstract PollOption toPollOption(UpdatePollOptionRequest updatePollOptionRequest);
}
