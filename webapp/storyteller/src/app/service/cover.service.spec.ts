/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { CoverService } from './cover.service';

describe('Service: Cover', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CoverService]
    });
  });

  it('should ...', inject([CoverService], (service: CoverService) => {
    expect(service).toBeTruthy();
  }));
});
