import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { map, take } from 'rxjs';
import { MyenergiService } from './myenergi.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  private now = new Date();
  public  maximumDate = new Date(Date.now() - 86400000);
  public minimumDate = new Date(2021,0, 1);
  public dateRange = new FormGroup({
    start: new FormControl(this.now),
    end: new FormControl(this.now)
  });
  
  public constructor(
    private readonly datePipe: DatePipe,
    private readonly service: MyenergiService
  ) {}

  public ngOnInit(): void {
    this.dateRange.controls['start'].value;
  }

  public today(): void {
    this.dateRange.patchValue({start: this.now, end: this.now});
  }

  public download(): void {
    this.service.latestData.pipe(
      take(1),
      map(data => {
        // TODO map data to csv
        return "";
      })
    ).subscribe((data) => {
      const blob = new Blob([data], { type: 'text/csv' });
      const url= window.URL.createObjectURL(blob);
      const a = document.createElement('a');
      document.body.appendChild(a);
      a.href = url;
      a.download = `myenergi-${this.datePipe.transform(this.dateRange.controls['start'].value, 'yyyy.MM.dd')}-${this.datePipe.transform(this.dateRange.controls['end'].value, 'yyyy.MM.dd')}`;
      a.click();
      setTimeout(() => {
        window.URL.revokeObjectURL(url);
        document.body.removeChild(a);
      }, 0)
    });
  }
}
