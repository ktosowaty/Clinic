import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClinicRegisterComponent } from './clinic-register.component';

describe('ClinicRegisterComponent', () => {
  let component: ClinicRegisterComponent;
  let fixture: ComponentFixture<ClinicRegisterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClinicRegisterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClinicRegisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
