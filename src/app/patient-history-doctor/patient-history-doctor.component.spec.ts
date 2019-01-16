import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientHistoryDoctorComponent } from './patient-history-doctor.component';

describe('PatientHistoryDoctorComponent', () => {
  let component: PatientHistoryDoctorComponent;
  let fixture: ComponentFixture<PatientHistoryDoctorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PatientHistoryDoctorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PatientHistoryDoctorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
