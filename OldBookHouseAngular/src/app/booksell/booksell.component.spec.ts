import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BooksellComponent } from './booksell.component';

describe('BooksellComponent', () => {
  let component: BooksellComponent;
  let fixture: ComponentFixture<BooksellComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BooksellComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BooksellComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
