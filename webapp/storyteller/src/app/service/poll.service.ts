import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Poll} from "../model/poll";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class PollService {

  constructor(private http: HttpClient) { }

  getPoll(pollId: string): Observable<Poll> {
    return this.http.get<Poll>(`${environment.apiUrl}/poll/${pollId}`);
  }

  getVoteState(pollId: string): Observable<boolean> {
    return this.http.get<boolean>(`${environment.apiUrl}/poll/${pollId}/voteState`);
  }

  vote(pollId: string, optionId: string): Observable<Poll> {
    return this.http.post<Poll>(`${environment.apiUrl}/poll/${pollId}/vote/${optionId}`, null);
  }
}
