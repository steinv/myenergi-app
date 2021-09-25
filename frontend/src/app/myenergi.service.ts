import { DatePipe } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { take } from 'rxjs/operators';
import { Configuration } from './configuration';

export interface TODO {

}

export interface HistoryCall {
  days: Array<DayCall>;
}
export interface DayCall {
  date: string;
  serial: string;
  generated: number;
  imported: number;
  exported: number;
  charged: number;
  consumed: number;
}

@Injectable()
export class MyenergiService {

  constructor(
    private readonly _httpClient: HttpClient, 
    private readonly _config: Configuration,
    private readonly _datePipe: DatePipe,
  ) { }

  public getStatus(): Observable<TODO> {
    return this.doCall<TODO>('/zappi');
  }

  public getStatusZappi(serial?: string): Observable<TODO> {
    const zappiSerial = serial || this._config.zappi;
    return this.doCall<TODO>('/zappi/'+zappiSerial);
  }

  public getHistoryOnDate(date: Date, serial?: string): Observable<DayCall> {
    const zappiSerial = serial || this._config.zappi;
    return this.doCall<DayCall>('/zappi/'+zappiSerial+"/"+this._datePipe.transform(date, 'yyyy-MM-dd'));
  }

  public getHistoryInRage(start: Date, end: Date, serial?: string): Observable<HistoryCall> {
    const zappiSerial = serial || this._config.zappi;
    return this.doCall<HistoryCall>('/zappi/'+zappiSerial+"/"+this._datePipe.transform(start, 'yyyy-MM-dd')+"/"+this._datePipe.transform(end, 'yyyy-MM-dd'));
  }

  public liveView(serial: string, date: Date): Observable<TODO> {
    const zappiSerial = serial || this._config.zappi;
    return this.doCall<TODO>('/zappi/'+zappiSerial+"/"+this._datePipe.transform(date, 'yyyy-MM-dd'));
  }

  private doCall<T>(resource: string) {
    return this._httpClient.get<T>(this._config.url + resource).pipe(take(1));
  }
}
