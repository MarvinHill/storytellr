import {ChangeDetectorRef, Component, Input, OnInit} from '@angular/core';
import {Poll, PollOption} from "../../../model/poll";
import {PollService} from "../../../service/poll.service";
import {KeycloakService} from "keycloak-angular";

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


    console.log("Poll Input", this.poll);
  }

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

  calculateToalVotes(poll: Poll): number {
    let totalVotes = 0;
    poll.options.forEach(option => {
      totalVotes += option.voteCount;
    });
    return totalVotes;
  }

  voteForOption(option: PollOption) {
    if(this.poll && !this.voted) {
      this.pollService.vote(this.poll.id, option.id);
      option.voteCount += 1;
      this.voted = true;
      if(this.pollId !== undefined) {
        this.pollService.vote(this.pollId, option.id).subscribe((lpoll) => {
          this.poll = lpoll;
          console.log("Voted Poll update ", this.poll);
        });
      }

    }
  }

}
