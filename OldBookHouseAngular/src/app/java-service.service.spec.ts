import { TestBed } from '@angular/core/testing';

import { JavaServiceService } from './java-service.service';

describe('JavaServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: JavaServiceService = TestBed.get(JavaServiceService);
    expect(service).toBeTruthy();
  });
});
