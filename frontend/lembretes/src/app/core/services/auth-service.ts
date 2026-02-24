import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { jwtDecode } from 'jwt-decode';
import { tap } from 'rxjs';
import { Role } from '../config/menu.config';

interface LoginResponse {
  acess_token: string
}

type JwtPayload = {exp?: number}

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly KEY = 'token'
  
  constructor (private http: HttpClient){}

  private isTokenExpired(token: string): boolean{
    try{
      const {exp} = jwtDecode<JwtPayload>(token);
      if(!exp) return false;

      const now = Math.floor(Date.now() / 1000)
      return now >= exp
    }catch{
      return true
    }

  }

  login(username: string, password: string ){
    return this.http.post<LoginResponse>('http://localhost:8080/api/auth/login', {username, password}).pipe(
      tap(res => localStorage.setItem(this.KEY, res.acess_token))
    )

    
  }



  hasRole(itemRole: Role[]){
    const token = String(this.getToken())
    const {scope} = jwtDecode<{scope?: string}>(token)

    const roles = (scope  ?? '').split(/\s+/).filter(s => s.startsWith('ROLE_')) as Role[]
    const haveRole = roles.some(r => itemRole.includes(r)) 
    return haveRole 
  }

  getToken(): string | null {
    const token = localStorage.getItem(this.KEY)
    if(!token || token === 'undefined' || token === 'null') return null
    if(this.isTokenExpired(token)){
      localStorage.removeItem(this.KEY)
    }
    return token

  }

  logout(): void {
    return localStorage.removeItem(this.KEY)
  }
  
}
