import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';

import { SearchCriterion } from 'src/app/domain/search-criterion';

@Injectable({
  providedIn: 'root'
})
export class MedicalEquipmentService {
  private medicalEquipmentControllerURL: string = 'http://localhost:8080/medical-equipment';
  private findEquipmentByCompanyNameURL: string = this.medicalEquipmentControllerURL.concat('/of-company');
  private searchEquipmentByNameURL: string = this.medicalEquipmentControllerURL.concat('/search-by-name');
  private searchEquipmentOfCompanyByNameURL: string = this.searchEquipmentByNameURL.concat('/of-company');
  
  constructor(private httpClient: HttpClient) { }
  
  findAll(): Observable<any> {
    let headers: HttpHeaders = new HttpHeaders({
      'Accept': 'application/json'
    });

    return this.httpClient.get(this.medicalEquipmentControllerURL, { headers: headers });
  }

  findById(id: number): Observable<any> {
    let headers: HttpHeaders = new HttpHeaders({
      'Accept': 'application/json'
    });

    return this.httpClient.get(this.medicalEquipmentControllerURL.concat(`/${id}`), { headers: headers });
  }

  findByCompanyName(companyName: string): Observable<any> {
    let headers: HttpHeaders = new HttpHeaders({
      'Accept': 'application/json'
    });

    return this.httpClient.get(this.findEquipmentByCompanyNameURL.concat(`/${companyName}`), { headers: headers });
  }

  searchEquipmentByName(searchCriterion: SearchCriterion): Observable<any> {
    let headers: HttpHeaders = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    });

    return this.httpClient.post(this.searchEquipmentByNameURL, JSON.stringify(searchCriterion), { headers: headers });
  }

  searchEquipmentOfCompanyByName(searchCriterion: SearchCriterion, companyName: string): Observable<any> {
    let headers: HttpHeaders = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    });

    return this.httpClient.post(this.searchEquipmentOfCompanyByNameURL.concat(`/${companyName}`), 
        JSON.stringify(searchCriterion), { headers: headers });
  }
}
