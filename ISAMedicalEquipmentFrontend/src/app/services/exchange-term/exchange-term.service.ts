import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';

import { ExchangeTerm } from 'src/app/domain/term/exchange-term';
import { DateTimeWrapper } from 'src/app/domain/date-time-wrapper';

@Injectable({
  providedIn: 'root'
})
export class ExchangeTermService {
  private exchangeTermsControllerURL: string = 'http://localhost:8080/exchange-terms';
  private findAllTermsOnSpecificDateURL: string = 
      this.exchangeTermsControllerURL.concat('/on-specific-date');
  private findAllTermsOnSpecificDateOfCompanyURL: string = 
      this.exchangeTermsControllerURL.concat('/on-specific-date-of-company');
  private findAllTermsOfCompanyURL: string = this.exchangeTermsControllerURL.concat('/of-company');
  private reserveTermURL: string = this.exchangeTermsControllerURL.concat('/reserve');
  
  constructor(private httpClient: HttpClient) { }
  
  findAll(): Observable<any> {
    let headers: HttpHeaders = new HttpHeaders({
      'Accept': 'application/json'
    });

    return this.httpClient.get(this.exchangeTermsControllerURL, { headers: headers });
  }

  findById(id: number): Observable<any> {
    let headers: HttpHeaders = new HttpHeaders({
      'Accept': 'application/json'
    });

    return this.httpClient.get(this.exchangeTermsControllerURL.concat(`/${id}`), { headers: headers });
  }

  findAllOnSpecificDate(dateWrapper: DateTimeWrapper): Observable<any> {
    let headers: HttpHeaders = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    });

    return this.httpClient.post(this.findAllTermsOnSpecificDateURL, JSON.stringify(dateWrapper), { headers: headers });
  }

  findAllOnSpecificDateOfCompany(dateWrapper: DateTimeWrapper, companyId: number): Observable<any> {
    let headers: HttpHeaders = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    });

    return this.httpClient.post(this.findAllTermsOnSpecificDateOfCompanyURL.concat(`/${companyId}`), 
        JSON.stringify(dateWrapper), { headers: headers });
  }

  findAllOfCompany(companyId: number): Observable<any> {
    let headers: HttpHeaders = new HttpHeaders({
      'Accept': 'application/json'
    });

    return this.httpClient.get(this.findAllTermsOfCompanyURL.concat(`/${companyId}`), { headers: headers });
  }

  reserveTerm(term: ExchangeTerm): Observable<any> {
    let headers: HttpHeaders = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    });

    return this.httpClient.post(this.reserveTermURL, JSON.stringify(term), { headers: headers });
  }
}
