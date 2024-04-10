import { Injectable } from '@angular/core';
import * as jose from 'jose'
import {JWTPayload} from 'jose'

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  constructor() { }

  getUserIdFromJWT(token: string): any {
    let decoded : JWTPayload = jose.decodeJwt(token)
    return decoded.sub
  }
}
