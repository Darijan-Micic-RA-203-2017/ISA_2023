import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
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
  private cancelOrderURL: string = this.orderingControllerURL.concat('/cancel-order');

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
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    });

    return this.httpClient.post(this.generateQRCodeURL, JSON.stringify(newEquipmentOrder), { headers: headers });
  }

  cancelOrder(exchangeTermId: number, procurementManagerId: number): Observable<any> {
    let headers: HttpHeaders = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    });
    // REFERENCE: https://www.concretepage.com/angular/angular-httpclient-delete#HttpParams
    let params: HttpParams = new HttpParams()
        .set('exchangeTermId', exchangeTermId).set('procurementManagerId', procurementManagerId);

    return this.httpClient.delete(this.cancelOrderURL, { headers: headers, params: params });
  }
}
