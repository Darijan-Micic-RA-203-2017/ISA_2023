import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';

import { ProcurementManagerRegistrationReq } from 'src/app/domain/procurement-manager-registration-req';
import { UserCodeWrapper } from 'src/app/domain/user-code-wrapper';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private usersControllerURL: string = 'http://localhost:8080/users';
  private procurementManagerRegistrationURL: string = 
      this.usersControllerURL.concat('/register-as-a-procurement-manager');
  private accountActivationURL: string = 
      this.usersControllerURL.concat('/activate-account');
  
  constructor(private httpClient: HttpClient) { }
  
  registerAsAProcurementManager(procurementManagerRegistrationReq: ProcurementManagerRegistrationReq): Observable<any> {
    let headers: HttpHeaders = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    });

    return this.httpClient.post(this.procurementManagerRegistrationURL, 
        JSON.stringify(procurementManagerRegistrationReq), { headers: headers });
  }

  activateAccount(codeOfNewRegisteredUserWrapper: UserCodeWrapper): Observable<any> {
    let headers: HttpHeaders = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    });

    return this.httpClient.post(this.accountActivationURL, JSON.stringify(codeOfNewRegisteredUserWrapper), 
        { headers: headers });
  }
}
