import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';

import { SearchCriterion } from 'src/app/domain/search-criterion';

@Injectable({
  providedIn: 'root'
})
export class MedicalEquipmentCompanyService {
  private medicalEquipmentCompaniesControllerURL: string = 'http://localhost:8080/medical-equipment-companies';
  private searchCompaniesByNameOrPopulatedPlaceURL: string = 
      this.medicalEquipmentCompaniesControllerURL.concat('/search-by-name-or-populated-place');
  
  constructor(private httpClient: HttpClient) { }
  
  findAll(): Observable<any> {
    const allMedicalEquipmentCompaniesRetrievalHeaders = new HttpHeaders({
      'Accept': 'application/json'
    });

    return this.httpClient.get(this.medicalEquipmentCompaniesControllerURL, 
        { headers: allMedicalEquipmentCompaniesRetrievalHeaders });
  }

  findById(id: number): Observable<any> {
    const medicalEquipmentCompanyRetrievalByIdHeaders = new HttpHeaders({
      'Accept': 'application/json'
    });

    return this.httpClient.get(this.medicalEquipmentCompaniesControllerURL.concat(`/${id}`), 
        { headers: medicalEquipmentCompanyRetrievalByIdHeaders });
  }

  searchByNameOrPopulatedPlace(searchCriterion: SearchCriterion): Observable<any> {
    const searchByNameOrPopulatedPlaceHeaders = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    });

    return this.httpClient.post(this.searchCompaniesByNameOrPopulatedPlaceURL, JSON.stringify(searchCriterion), 
        { headers: searchByNameOrPopulatedPlaceHeaders });
  }
}
