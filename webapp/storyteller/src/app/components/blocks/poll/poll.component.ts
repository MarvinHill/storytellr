import {Component, Input, OnInit} from '@angular/core';
import {Poll, PollOption} from "../../../model/poll";

@Component({
  selector: 'app-poll',
  templateUrl: './poll.component.html',
  styleUrls: ['./poll.component.css']
})
export class PollComponent implements OnInit {

  @Input() poll : Poll | undefined;

  constructor() { }

  ngOnInit() {
    console.log("Poll", this.poll);
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

}
