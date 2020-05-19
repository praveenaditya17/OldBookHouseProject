import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BookDeliverAddressComponent } from './book-deliver-address.component';

describe('BookDeliverAddressComponent', () => {
  let component: BookDeliverAddressComponent;
  let fixture: ComponentFixture<BookDeliverAddressComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BookDeliverAddressComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BookDeliverAddressComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
