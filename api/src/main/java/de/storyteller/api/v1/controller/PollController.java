package de.storyteller.api.v1.controller;

import de.storyteller.api.service.poll.PollService;
import de.storyteller.api.v1.dto.poll.CreatePollOptionRequest;
import de.storyteller.api.v1.dto.poll.CreatePollRequest;
import de.storyteller.api.v1.dto.poll.DeletePollOptionRequest;
import de.storyteller.api.v1.dto.poll.DeletePollRequest;
import de.storyteller.api.v1.dto.poll.PollDTO;
import de.storyteller.api.v1.dto.poll.UpdatePollOptionRequest;
import de.storyteller.api.v1.dto.poll.UpdatePollRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/polls")
@AllArgsConstructor
public class PollController {

  PollService pollService;


  @PreAuthorize("isAuthenticated() || @sAuthService.userOwnsBook(createRequest.getBookId())")
  @PostMapping("/create")
  public ResponseEntity<PollDTO> createPoll(@Valid @RequestBody CreatePollRequest createRequest) {
    try {
      PollDTO dto =  pollService.createPoll(createRequest);
      return ResponseEntity.ok(dto);
    }catch(RuntimeException e){
      return ResponseEntity.internalServerError().build();
    }
  }

  @PreAuthorize("isAuthenticated() || @sAuthService.userOwnsPoll(updateRequest.getPollId())")
  @PutMapping("/update")
  public ResponseEntity<PollDTO> updatePoll(@Valid @RequestBody UpdatePollRequest updateRequest){
    try {
      PollDTO dto =  pollService.updatePoll(updateRequest);
      return ResponseEntity.ok(dto);
    }catch(RuntimeException e){
      return ResponseEntity.internalServerError().build();
    }
  }

  @PreAuthorize("isAuthenticated() || @sAuthService.userOwnsPoll(deletePollRequest.getPollId())")
  @DeleteMapping("/delete")
  public ResponseEntity<?> deletePoll(@Valid @RequestBody DeletePollRequest deletePollRequest) {
    try {
      pollService.deletePoll(deletePollRequest);
      return ResponseEntity.ok().build();
    }catch(RuntimeException e){
      return ResponseEntity.internalServerError().build();
    }
  }

  @PreAuthorize("isAuthenticated() || @sAuthService.userOwnsPoll(createPollOptionRequest.getPollId())")
  @PostMapping("/options/create")
  public ResponseEntity<PollDTO> createPollOption(@Valid @RequestBody
                                                  CreatePollOptionRequest createPollOptionRequest) {
    try {
      PollDTO dto =  pollService.createPollOption(createPollOptionRequest);
      return ResponseEntity.ok(dto);
    }catch(RuntimeException e){
      return ResponseEntity.internalServerError().build();
    }
  }

  @PreAuthorize("isAuthenticated() || @sAuthService.userOwnsPoll(deleteRequest.getPollId())")
  @DeleteMapping("/options/delete")
  public ResponseEntity<PollDTO> deletePollOption(@Valid @RequestBody
  DeletePollOptionRequest deleteRequest) {
    try {
      PollDTO dto =  pollService.deletePollOption(deleteRequest);
      return ResponseEntity.ok(dto);
    }catch(RuntimeException e){
      return ResponseEntity.internalServerError().build();
    }
  }

  @PreAuthorize("isAuthenticated() || @sAuthService.userOwnsPoll(updateRequest.getPollId())")
  @PutMapping("/options/update")
  public ResponseEntity<PollDTO>updatePollOption(@Valid @RequestBody UpdatePollOptionRequest updateRequest) {
    try {
      PollDTO dto =  pollService.updatePollOption(updateRequest);
      return ResponseEntity.ok(dto);
    }catch(RuntimeException e){
      return ResponseEntity.internalServerError().build();
    }
  }
}
