import { TestBed } from '@angular/core/testing';

import { OnlyAuthenticatedUsersGuard } from './only-authenticated-users.guard';

describe('OnlyAuthenticatedUsersGuard', () => {
  let guard: OnlyAuthenticatedUsersGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(OnlyAuthenticatedUsersGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
