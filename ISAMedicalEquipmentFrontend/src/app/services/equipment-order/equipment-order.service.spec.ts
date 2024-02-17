import { TestBed } from '@angular/core/testing';

import { EquipmentOrderService } from './equipment-order.service';

describe('EquipmentOrderService', () => {
  let service: EquipmentOrderService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EquipmentOrderService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
