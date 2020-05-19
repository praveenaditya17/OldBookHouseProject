import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateBookStatusComponent } from './update-book-status.component';

describe('UpdateBookStatusComponent', () => {
  let component: UpdateBookStatusComponent;
  let fixture: ComponentFixture<UpdateBookStatusComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateBookStatusComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateBookStatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
