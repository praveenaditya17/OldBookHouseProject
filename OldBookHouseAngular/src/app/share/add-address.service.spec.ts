import { TestBed } from '@angular/core/testing';

import { AddAddressService } from './add-address.service';

describe('AddAddressService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AddAddressService = TestBed.get(AddAddressService);
    expect(service).toBeTruthy();
  });
});
