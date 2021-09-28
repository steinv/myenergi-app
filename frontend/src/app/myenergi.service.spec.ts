import { TestBed } from '@angular/core/testing';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import { MyenergiService } from './myenergi.service';
import { Configuration } from './configuration';
import { DatePipe } from '@angular/common';

describe('MyenergiService', () => {
  let service: MyenergiService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [
        DatePipe,
        MyenergiService,
        Configuration,
      ]
    });
    service = TestBed.inject(MyenergiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
