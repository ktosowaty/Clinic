import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SecretaryRegisterComponent } from './secretary-register.component';

describe('SecretaryRegisterComponent', () => {
  let component: SecretaryRegisterComponent;
  let fixture: ComponentFixture<SecretaryRegisterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SecretaryRegisterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SecretaryRegisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
