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
  
  constructor(private httpClient: HttpClient) { }
  
  findAll(): Observable<any> {
    const allMedicalEquipmentRetrievalHeaders = new HttpHeaders({
      'Accept': 'application/json'
    });

    return this.httpClient.get(this.medicalEquipmentControllerURL, 
        { headers: allMedicalEquipmentRetrievalHeaders });
  }

  findById(id: number): Observable<any> {
    const medicalEquipmentRetrievalByIdHeaders = new HttpHeaders({
      'Accept': 'application/json'
    });

    return this.httpClient.get(this.medicalEquipmentControllerURL.concat(`/${id}`), 
        { headers: medicalEquipmentRetrievalByIdHeaders });
  }

  searchByName(searchCriterion: SearchCriterion): Observable<any> {
    const searchByNameHeaders = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    });

    return this.httpClient.post(this.searchEquipmentByNameURL, JSON.stringify(searchCriterion), 
        { headers: searchByNameHeaders });
  }
}
