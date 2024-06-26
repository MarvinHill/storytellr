import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Poll} from "../model/poll";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";

/**
 * PollService is a service that provides methods for interacting with the poll API.
 * @class
 * @property {HttpClient} http - The HttpClient for making HTTP requests.
 */
@Injectable({
  providedIn: 'root'
})
export class PollService {

  /**
   * Constructor for the PollService.
   * @param {HttpClient} http - The HttpClient for making HTTP requests.
   */
  constructor(private http: HttpClient) { }

  /**
   * Gets a poll by its ID.
   * @param {string} pollId - The ID of the poll.
   * @returns {Observable<Poll>} An Observable that contains the poll.
   */
  getPoll(pollId: string): Observable<Poll> {
    return this.http.get<Poll>(`${environment.apiUrl}/poll/${pollId}`);
  }

  /**
   * Gets the vote state of a poll.
   * @param {string} pollId - The ID of the poll.
   * @returns {Observable<boolean>} An Observable that contains the vote state of the poll.
   */
  getVoteState(pollId: string): Observable<boolean> {
    return this.http.get<boolean>(`${environment.apiUrl}/poll/${pollId}/voteState`);
  }

  /**
   * Votes for an option in a poll.
   * @param {string} pollId - The ID of the poll.
   * @param {string} optionId - The ID of the option.
   * @returns {Observable<Poll>} An Observable that contains the updated poll.
   */
  vote(pollId: string, optionId: string): Observable<Poll> {
    return this.http.post<Poll>(`${environment.apiUrl}/poll/${pollId}/vote/${optionId}`, null);
  }
}
