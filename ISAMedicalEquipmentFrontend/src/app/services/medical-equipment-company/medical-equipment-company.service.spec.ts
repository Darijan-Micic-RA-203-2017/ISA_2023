import { TestBed } from '@angular/core/testing';

import { MedicalEquipmentCompanyService } from './medical-equipment-company.service';

describe('MedicalEquipmentCompanyService', () => {
  let service: MedicalEquipmentCompanyService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MedicalEquipmentCompanyService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
