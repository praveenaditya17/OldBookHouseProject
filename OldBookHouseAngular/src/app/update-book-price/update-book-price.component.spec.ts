import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateBookPriceComponent } from './update-book-price.component';

describe('UpdateBookPriceComponent', () => {
  let component: UpdateBookPriceComponent;
  let fixture: ComponentFixture<UpdateBookPriceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateBookPriceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateBookPriceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
