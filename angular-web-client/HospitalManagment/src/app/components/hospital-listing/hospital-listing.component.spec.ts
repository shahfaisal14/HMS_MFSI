import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HospitalListingComponent } from './hospital-listing.component';

describe('HospitalListingComponent', () => {
  let component: HospitalListingComponent;
  let fixture: ComponentFixture<HospitalListingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HospitalListingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HospitalListingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
