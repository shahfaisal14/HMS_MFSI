import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateHeadComponent } from './create-head.component';

describe('CreateHeadComponent', () => {
  let component: CreateHeadComponent;
  let fixture: ComponentFixture<CreateHeadComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateHeadComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateHeadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
