import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { JwtHelperService } from '@auth0/angular-jwt';

import { Observable } from 'rxjs';

import { Credentials } from 'src/app/domain/credentials';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private authControllerURL: string = 'http://localhost:8080/auth';
  private loginURL: string = this.authControllerURL.concat('/login');
  
  constructor(private jwtHelper: JwtHelperService, private httpClient: HttpClient) { }
  
  loginWith(credentials: Credentials): Observable<any> {
    const loginHeaders = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    });

    return this.httpClient.post(this.loginURL, JSON.stringify(credentials), { headers: loginHeaders });
  }

  getToken(): string | null {
    return localStorage.getItem('jwtToken');
  }
  
  getAuthStatus(): boolean {
    return !!localStorage.getItem('jwtToken');
  }

  hasTokenExpired(): boolean {
    let token = this.getToken();
    if (token) {
      if (this.jwtHelper.isTokenExpired(token)) {
        return true;
      }

      return false;
    }
    
    return true;
  }

  getUserId(): number {
    let userIdAsString: string | null = localStorage.getItem('userId');
    if (!userIdAsString) {
      return 0;
    }

    return Number.parseInt(userIdAsString);
  }

  getRole(): string | null {
    return localStorage.getItem('role');
  }

  isUserAProcurementManager(): boolean {
    let answer: boolean = false;

    let role = this.getRole();
    if (role) {
      if (role == 'ROLE_PROCUREMENT_MANAGER') {
        answer = true;
      }
    }

    return answer;
  }

  isUserACompanyAdministrator(): boolean {
    let answer: boolean = false;

    let role = this.getRole();
    if (role) {
      if (role == 'ROLE_COMPANY_ADMINISTRATOR') {
        answer = true;
      }
    }

    return answer;
  }

  isUserASystemAdministrator(): boolean {
    let answer: boolean = false;

    let role = this.getRole();
    if (role) {
      if (role == 'ROLE_SYSTEM_ADMINISTRATOR') {
        answer = true;
      }
    }

    return answer;
  }
}
