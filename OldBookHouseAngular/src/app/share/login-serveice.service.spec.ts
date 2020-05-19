import { TestBed } from '@angular/core/testing';

import { LoginServeiceService } from './login-serveice.service';

describe('LoginServeiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: LoginServeiceService = TestBed.get(LoginServeiceService);
    expect(service).toBeTruthy();
  });
});
