import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UsecaseEditComponent } from './usecase-edit.component';

describe('UsecaseEditComponent', () => {
  let component: UsecaseEditComponent;
  let fixture: ComponentFixture<UsecaseEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UsecaseEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UsecaseEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
