import { TestBed } from '@angular/core/testing';

import { BookSellSearchService } from './book-sell-search.service';

describe('BookSellSearchService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: BookSellSearchService = TestBed.get(BookSellSearchService);
    expect(service).toBeTruthy();
  });
});
