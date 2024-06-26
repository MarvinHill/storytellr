import { Injectable } from '@angular/core';
import * as jose from 'jose'
import {JWTPayload} from 'jose'

@Injectable({
  providedIn: 'root'
})

/**
 * Service for Users
 */
export class UserService {

  constructor() { }

  /**
   * Get the user id from a JWT
   * @param token
   */
  getUserIdFromJWT(token: string): any {
    let decoded : JWTPayload = jose.decodeJwt(token)
    return decoded.sub
  }
}
