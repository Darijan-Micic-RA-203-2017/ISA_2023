import { TestBed } from '@angular/core/testing';

import { MedicalEquipmentService } from './medical-equipment.service';

describe('MedicalEquipmentService', () => {
  let service: MedicalEquipmentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MedicalEquipmentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
