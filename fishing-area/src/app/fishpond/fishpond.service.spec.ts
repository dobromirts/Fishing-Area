import { TestBed } from '@angular/core/testing';

import { FishpondService } from './fishpond.service';

describe('FishpondService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: FishpondService = TestBed.get(FishpondService);
    expect(service).toBeTruthy();
  });
});
