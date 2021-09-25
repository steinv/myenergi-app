import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { take } from 'rxjs/operators';
import { Configuration } from './configuration';

export interface TODO {

}

export interface HistoryCall {
  generated: number;
  imported: number;
  exported: number;
  charged: number;
  consumed: number;
}

@Injectable()
export class MyenergiService {

  constructor(private readonly _httpClient: HttpClient, private readonly _config: Configuration) { }

  public getStatus(): Observable<TODO> {
    return this.doCall<TODO>('/zappi');
  }

  public getStatusZappi(serial?: string): Observable<TODO> {
    const zappiSerial = serial || this._config.zappi;
    return this.doCall<TODO>('/zappi/'+zappiSerial);
  }

  public getHistory(date: Date, serial?: string): Observable<HistoryCall> {
    const zappiSerial = serial || this._config.zappi;
    return this.doCall<HistoryCall>('/zappi/'+zappiSerial+"/"+date.getFullYear()+"/"+(date.getMonth()+1)+"/"+date.getDate());
  }

  public liveView(serial: string, date: Date): Observable<TODO> {
    const zappiSerial = serial || this._config.zappi;
    return this.doCall<TODO>('/zappi/'+zappiSerial+"/"+date.getFullYear()+"/"+(date.getMonth()+1)+"/"+date.getDate());
  }

  private doCall<T>(resource: string) {
    return this._httpClient.get<T>(this._config.url + resource).pipe(take(1));
  }
}
