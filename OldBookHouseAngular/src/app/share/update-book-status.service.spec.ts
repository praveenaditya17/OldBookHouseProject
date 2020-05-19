import { TestBed } from '@angular/core/testing';

import { UpdateBookStatusService } from './update-book-status.service';

describe('UpdateBookStatusService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: UpdateBookStatusService = TestBed.get(UpdateBookStatusService);
    expect(service).toBeTruthy();
  });
});
