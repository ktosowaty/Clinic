import { TestBed } from '@angular/core/testing';

import { PatientHistoryService } from './patient-history.service';

describe('PatientHistoryService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PatientHistoryService = TestBed.get(PatientHistoryService);
    expect(service).toBeTruthy();
  });
});
