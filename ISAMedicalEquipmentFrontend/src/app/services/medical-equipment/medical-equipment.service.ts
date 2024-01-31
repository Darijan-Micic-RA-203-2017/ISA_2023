import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';

import { SearchCriterion } from 'src/app/domain/search-criterion';

@Injectable({
  providedIn: 'root'
})
export class MedicalEquipmentService {
  private medicalEquipmentControllerURL: string = 'http://localhost:8080/medical-equipment';
  private searchEquipmentByNameURL: string = this.medicalEquipmentControllerURL.concat('/search-by-name');
  private findAllEquipmentOfCompanyURL: string = this.medicalEquipmentControllerURL.concat('/find-all-of-company');
  
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

  searchEquipmentByName(searchCriterion: SearchCriterion): Observable<any> {
    let headers: HttpHeaders = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    });

    return this.httpClient.post(this.searchEquipmentByNameURL, JSON.stringify(searchCriterion), { headers: headers });
  }

  searchEquipmentOfCompanyByName(searchCriterion: SearchCriterion, companyId: number): Observable<any> {
    let headers: HttpHeaders = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    });

    return this.httpClient.post(this.searchEquipmentByNameURL.concat(`/${companyId}`), JSON.stringify(searchCriterion), 
        { headers: headers });
  }

  findAllOfCompany(companyId: number): Observable<any> {
    let headers: HttpHeaders = new HttpHeaders({
      'Accept': 'application/json'
    });

    return this.httpClient.get(this.findAllEquipmentOfCompanyURL.concat(`/${companyId}`), { headers: headers });
  }
}
