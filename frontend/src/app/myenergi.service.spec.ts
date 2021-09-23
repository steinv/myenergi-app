import { TestBed } from '@angular/core/testing';

import { MyenergiService } from './myenergi.service';

describe('MyenergiService', () => {
  let service: MyenergiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MyenergiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
