import { TestBed } from '@angular/core/testing';

import { BookMapperService } from './book-mapper.service';

describe('BookMapperService', () => {
  let service: BookMapperService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BookMapperService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
