import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BookSellSearchComponent } from './book-sell-search.component';

describe('BookSellSearchComponent', () => {
  let component: BookSellSearchComponent;
  let fixture: ComponentFixture<BookSellSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BookSellSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BookSellSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
