import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicalBenefitsComponent } from './medical-benefits.component';

describe('MedicalBenefitsComponent', () => {
  let component: MedicalBenefitsComponent;
  let fixture: ComponentFixture<MedicalBenefitsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MedicalBenefitsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MedicalBenefitsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
