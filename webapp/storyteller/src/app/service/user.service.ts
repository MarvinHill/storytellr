import { Injectable } from '@angular/core';
import * as jose from 'jose'
import {JWTPayload} from 'jose'

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor() { }

  getUserIdFromJWT(token: string): any {
    let decoded : JWTPayload = jose.decodeJwt(token)
    return decoded.sub
  }
}
