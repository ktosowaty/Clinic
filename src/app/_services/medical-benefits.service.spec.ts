import { TestBed } from '@angular/core/testing';

import { MedicalBenefitsService } from './medical-benefits.service';

describe('MedicalBenefitsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MedicalBenefitsService = TestBed.get(MedicalBenefitsService);
    expect(service).toBeTruthy();
  });
});
