import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';

import { OrderCreation } from 'src/app/domain/order/order-creation';

@Injectable({
  providedIn: 'root'
})
export class OrderingService {
  private orderingControllerURL: string = 'http://localhost:8080/ordering';
  private createOrderURL: string = this.orderingControllerURL.concat('/create-order');

  constructor(private httpClient: HttpClient) { }

  createOrder(orderCreation: OrderCreation): Observable<any> {
    let headers: HttpHeaders = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    });

    return this.httpClient.post(this.createOrderURL, JSON.stringify(orderCreation), { headers: headers });
  }
}
