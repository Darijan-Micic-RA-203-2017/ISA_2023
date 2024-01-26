import { TestBed } from '@angular/core/testing';

import { ExchangeTermService } from './exchange-term.service';

describe('ExchangeTermService', () => {
  let service: ExchangeTermService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ExchangeTermService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
