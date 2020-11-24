import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserInfoUrlComponent } from './user-info-url.component';

describe('UserInfoUrlComponent', () => {
  let component: UserInfoUrlComponent;
  let fixture: ComponentFixture<UserInfoUrlComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserInfoUrlComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserInfoUrlComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
