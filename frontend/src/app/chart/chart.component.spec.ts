import { ComponentFixture, inject, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { AppModule } from '../app.module';
import { DayCall, MyenergiService } from '../myenergi.service';
import { MockLocationStrategy } from '@angular/common/testing';
import { RouterTestingModule } from '@angular/router/testing';

import { ChartComponent } from './chart.component';
import { LocationStrategy } from '@angular/common';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatNativeDateModule } from '@angular/material/core';
import { ChartsModule } from 'ng2-charts';
import { HttpClientModule } from '@angular/common/http';

describe('ChartComponent', () => {
  let component: ChartComponent;
  let fixture: ComponentFixture<ChartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        AppModule 
      ],
      providers: [
        { provide: LocationStrategy, useClass: MockLocationStrategy },
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should getHistory when the input changes',inject([MyenergiService], (service: MyenergiService) => {
    const date =  new Date();
    spyOn(service, 'getHistoryOnDate').and.returnValue(of({
      date: date.toISOString(),
      serial: '',
    } as DayCall));

    component.start = date;
    component.end = date;
    component.ngOnChanges();

    expect(service.getHistoryOnDate).toHaveBeenCalledOnceWith(date);
  }));
});
