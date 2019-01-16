import { TestBed } from '@angular/core/testing';

import { ClinicRegisterService } from './clinic-register.service';

describe('ClinicRegisterService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ClinicRegisterService = TestBed.get(ClinicRegisterService);
    expect(service).toBeTruthy();
  });
});
