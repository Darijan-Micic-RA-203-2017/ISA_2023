import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';

import { OrderCreation } from 'src/app/domain/order/order-creation';
import { EquipmentOrder } from 'src/app/domain/order/equipment-order';

@Injectable({
  providedIn: 'root'
})
export class OrderingService {
  private orderingControllerURL: string = 'http://localhost:8080/ordering';
  private createOrderURL: string = this.orderingControllerURL.concat('/create-order');
  private generateQRCodeURL: string = this.orderingControllerURL.concat('/generate-qr-code');

  constructor(private httpClient: HttpClient) { }

  createOrder(orderCreation: OrderCreation): Observable<any> {
    let headers: HttpHeaders = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    });

    return this.httpClient.post(this.createOrderURL, JSON.stringify(orderCreation), { headers: headers });
  }

  generateQRCode(newEquipmentOrder: EquipmentOrder): Observable<any> {
    let headers: HttpHeaders = new HttpHeaders({
      'Accept': 'image/png',
      'Content-Type': 'application/json'
    });

    // REFERENCE: https://stackoverflow.com/questions/55967908/angular-display-byte-array-as-image
    return this.httpClient.post(this.generateQRCodeURL, JSON.stringify(newEquipmentOrder), 
        { headers: headers, responseType: 'arraybuffer' });
  }
}
