import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VisitSpecializationComponent } from './visit-specialization.component';

describe('VisitSpecializationComponent', () => {
  let component: VisitSpecializationComponent;
  let fixture: ComponentFixture<VisitSpecializationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VisitSpecializationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VisitSpecializationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
