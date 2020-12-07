import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ErrorRoleComponent } from './error-role.component';

describe('ErrorRoleComponent', () => {
  let component: ErrorRoleComponent;
  let fixture: ComponentFixture<ErrorRoleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ErrorRoleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ErrorRoleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
