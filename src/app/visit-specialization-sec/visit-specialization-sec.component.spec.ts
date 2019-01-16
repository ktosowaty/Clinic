import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VisitSpecializationSecComponent } from './visit-specialization-sec.component';

describe('VisitSpecializationSecComponent', () => {
  let component: VisitSpecializationSecComponent;
  let fixture: ComponentFixture<VisitSpecializationSecComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VisitSpecializationSecComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VisitSpecializationSecComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
