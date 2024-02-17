import { TestBed } from '@angular/core/testing';

import { DetailsOfEquipmentOrderService } from './details-of-equipment-order.service';

describe('DetailsOfEquipmentOrderService', () => {
  let service: DetailsOfEquipmentOrderService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DetailsOfEquipmentOrderService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
