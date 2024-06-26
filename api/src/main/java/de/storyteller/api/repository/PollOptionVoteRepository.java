package de.storyteller.api.repository;

import de.storyteller.api.model.PollOptionVote;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PollOptionVoteRepository extends MongoRepository<PollOptionVote, String> {
  Optional<PollOptionVote> findByPollIdAndPollOptionIdAndUserId(String pollId, String pollOptionId, String userId);
  boolean existsByPollIdAndPollOptionIdAndUserId(String pollId, String pollOptionId, String userId);
  boolean existsByPollIdAndUserId(String pollId, String userId);
  Optional<PollOptionVote> findByPollIdAndUserId(String pollId, String userId);
}
