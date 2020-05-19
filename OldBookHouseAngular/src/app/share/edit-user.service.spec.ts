import { TestBed } from '@angular/core/testing';

import { EditUSerService } from './edit-user.service';

describe('EditUSerService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: EditUSerService = TestBed.get(EditUSerService);
    expect(service).toBeTruthy();
  });
});
