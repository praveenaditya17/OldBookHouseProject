import { TestBed } from '@angular/core/testing';

import { UpdateBookPriceService } from './update-book-price.service';

describe('UpdateBookPriceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: UpdateBookPriceService = TestBed.get(UpdateBookPriceService);
    expect(service).toBeTruthy();
  });
});
