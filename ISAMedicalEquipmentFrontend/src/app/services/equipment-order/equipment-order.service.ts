import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EquipmentOrderService {
  private equipmentOrderControllerURL: string = 'http://localhost:8080/equipment-orders';

  constructor(private httpClient: HttpClient) { }

  findAll(): Observable<any> {
    let headers: HttpHeaders = new HttpHeaders({
      'Accept': 'application/json'
    });

    return this.httpClient.get(this.equipmentOrderControllerURL, { headers: headers });
  }

  findById(id: number): Observable<any> {
    let headers: HttpHeaders = new HttpHeaders({
      'Accept': 'application/json'
    });

    return this.httpClient.get(this.equipmentOrderControllerURL.concat(`/${id}`), { headers: headers });
  }
}
