import {ChangeDetectorRef, Component, Input, OnInit} from '@angular/core';
import {Poll, PollOption} from "../../../model/poll";
import {PollService} from "../../../service/poll.service";
import {KeycloakService} from "keycloak-angular";

/**
 * Calculate the progress classes for the option component.
 * @returns {String} The progress classes for the option component.
 */
@Component({
  selector: 'app-poll',
  templateUrl: './poll.component.html',
  styleUrls: ['./poll.component.css']
})
export class PollComponent implements OnInit {
  @Input() pollId : string | undefined;
  poll : Poll | undefined;
  show : boolean = false;
  voted: boolean = false;

  /**
   * Constructor for the PollComponent.
   * @param {PollService} pollService - The service for handling poll data.
   * @param {KeycloakService} keycloak - The service for handling user authentication.
   */
  constructor(private pollService : PollService, private keycloak: KeycloakService) { }

  ngOnInit() {
    if(this.pollId !== undefined) {
      if(this.keycloak.isLoggedIn()){
        this.pollService.getVoteState(this.pollId).subscribe((voted) => {
          this.show = true;
          this.voted = voted;
        });
      }
      else {
        this.show = true;
        this.voted = true;
      }

    this.pollService.getPoll(this.pollId).subscribe((poll) => {
        this.poll = poll;
      });
    }
    else {
      console.error("Poll Id is not defined");
    }
  }

  /**
   * Calculate the progress of an option.
   * @param {PollOption | undefined} option - The option to calculate the progress for.
   * @returns {number} The progress of the option.
   */
  calculateProgress( option: PollOption | undefined): number {
    if(option === undefined || this.poll === undefined) {
      return 5;
    }
    const totalVotes = this.calculateToalVotes(this.poll);
    if(totalVotes === 0) {
      return 5;
    }
    return (option.voteCount / totalVotes) * 100;
  }

  /**
   * Calculate the total votes of a poll.
   * @param {Poll} poll - The poll to calculate the total votes for.
   * @returns {number} The total votes of the poll.
   */
  calculateToalVotes(poll: Poll): number {
    let totalVotes = 0;
    poll.options.forEach(option => {
      totalVotes += option.voteCount;
    });
    return totalVotes;
  }

  /**
   * Vote for an option.
   * @param {PollOption} option - The option to vote for.
   */
  voteForOption(option: PollOption) {
    if(this.poll && !this.voted) {
      this.pollService.vote(this.poll.id, option.id);
      option.voteCount += 1;
      this.voted = true;
      if(this.pollId !== undefined) {
        this.pollService.vote(this.pollId, option.id).subscribe((lpoll) => {
          this.poll = lpoll;
        });
      }

    }
  }

}
