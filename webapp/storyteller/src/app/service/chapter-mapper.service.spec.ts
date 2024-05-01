import { TestBed } from '@angular/core/testing';

import { ChapterMapperService } from './chapter-mapper.service';

describe('ChapterMapperService', () => {
  let service: ChapterMapperService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ChapterMapperService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
