import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DetailsOfEquipmentOrderService {
  private detailsOfEquipmentOrdersControllerURL: string = 'http://localhost:8080/details-of-equipment-orders';

  constructor(private httpClient: HttpClient) { }

  findAll(): Observable<any> {
    let headers: HttpHeaders = new HttpHeaders({
      'Accept': 'application/json'
    });

    return this.httpClient.get(this.detailsOfEquipmentOrdersControllerURL, { headers: headers });
  }

  findById(id: number): Observable<any> {
    let headers: HttpHeaders = new HttpHeaders({
      'Accept': 'application/json'
    });

    return this.httpClient.get(this.detailsOfEquipmentOrdersControllerURL.concat(`/${id}`), { headers: headers });
  }
}
