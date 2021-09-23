import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { take } from 'rxjs/operators';
import { Configuration } from './configuration';

export interface StatusCall {

}

@Injectable()
export class MyenergiService {

  constructor(private readonly _httpClient: HttpClient, private readonly _config: Configuration) { }

  public getStatus(): Observable<StatusCall> {
    return this.doCall<StatusCall>('/zappi');
  }

  private doCall<T>(resource: string) {
    console.log(this._config.url);
    return this._httpClient.get<T>(this._config.url + resource).pipe(take(1));
  }
}
