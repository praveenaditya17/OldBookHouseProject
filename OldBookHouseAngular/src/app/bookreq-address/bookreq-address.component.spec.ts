import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BookreqAddressComponent } from './bookreq-address.component';

describe('BookreqAddressComponent', () => {
  let component: BookreqAddressComponent;
  let fixture: ComponentFixture<BookreqAddressComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BookreqAddressComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BookreqAddressComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
