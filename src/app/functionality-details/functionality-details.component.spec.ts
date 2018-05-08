import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FunctionalityDetailsComponent } from './functionality-details.component';

describe('FunctionalityDetailsComponent', () => {
  let component: FunctionalityDetailsComponent;
  let fixture: ComponentFixture<FunctionalityDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FunctionalityDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FunctionalityDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
