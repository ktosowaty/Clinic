import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VisitSecretaryComponent } from './visit-secretary.component';

describe('VisitSecretaryComponent', () => {
  let component: VisitSecretaryComponent;
  let fixture: ComponentFixture<VisitSecretaryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VisitSecretaryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VisitSecretaryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
