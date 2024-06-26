package de.storyteller.api.repository;

import de.storyteller.api.model.PollOptionVote;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
/**
 * Repository for poll option votes
 */
public interface PollOptionVoteRepository extends MongoRepository<PollOptionVote, String> {
  /**
   * Find poll option vote by poll id, poll option id and user id
   * @param pollId the poll id
   * @param pollOptionId the poll option id
   * @param userId the user id
   * @return the poll option vote if it exists
   */
  Optional<PollOptionVote> findByPollIdAndPollOptionIdAndUserId(String pollId, String pollOptionId, String userId);
  /**
   * Check if a poll option vote exists by poll id, poll option id and user id
   * @param pollId the poll id
   * @param pollOptionId the poll option id
   * @param userId the user id
   * @return true if the poll option vote exists, false otherwise
   */
  boolean existsByPollIdAndPollOptionIdAndUserId(String pollId, String pollOptionId, String userId);
  /**
   * Check if a poll option vote exists by poll id and user id
   * @param pollId the poll id
   * @param userId the user id
   * @return true if the poll option vote exists, false otherwise
   */
  boolean existsByPollIdAndUserId(String pollId, String userId);
  /**
   * Find poll option vote by poll id and user id
   * @param pollId the poll id
   * @param userId the user id
   * @return the poll option vote if it exists
   */
  Optional<PollOptionVote> findByPollIdAndUserId(String pollId, String userId);
}
