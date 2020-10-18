import { TestBed } from '@angular/core/testing';

import { BackEndService } from './backend.service';

describe('BackEndService', () => {
  let service: BackEndService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BackEndService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
