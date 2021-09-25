import { DatePipe } from '@angular/common';
import { ChangeDetectionStrategy, ChangeDetectorRef, Component, Input, OnChanges } from '@angular/core';
import { ChartType, ChartOptions, ChartDataSets } from 'chart.js';
import { Label } from 'ng2-charts';
import { map } from 'rxjs/operators';
import { MyenergiService } from '../myenergi.service';

@Component({
  selector: 'app-chart',
  templateUrl: './chart.component.html',
  styleUrls: ['./chart.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ChartComponent implements OnChanges {

  @Input()
  start?: Date;

  @Input()
  end?: Date;

  public barChartOptions: ChartOptions = {
    responsive: true,
  };
  public barChartLabels: Label[] = [];
  public barChartType: ChartType = 'bar';
  public barChartLegend = true;
  public barChartPlugins = [];
  public barChartData: ChartDataSets[] = [];
  
  public constructor(
    private readonly _changeDetector: ChangeDetectorRef,
    private readonly _service: MyenergiService,
    private readonly _datePipe: DatePipe,
  ) { }

  public ngOnChanges(): void {
    this.updateChart();
  }

  private updateChart() {
    if(this.start && this.end) {
      if(this.start.getTime() == this.end.getTime()) {
        this.getChartForDate(this.start);
      } else {
        this.getChartForRange(this.start, this.end);
      }
    }
  }

    
  private getChartForDate(date: Date) {
    this._service.getHistoryOnDate(date).pipe(
      map(r => {
          this.barChartLabels = [this._datePipe.transform(new Date(r.date), 'dd/MM/yyyy')!];
          this.barChartData = [
            {
              data: [r.consumed/3600000],
              label: 'consumed'
            }
            ,          
            {
              data: [r.charged/3600000],
              label: 'EV charged'
            },          
            {
              data: [r.generated /3600000],
              label: 'solar-panels'
            },
            {
              data: [r.imported/3600000],
              label: 'imported'
            },
            {
              data: [r.exported/3600000],
              label: 'exported'
            },
          ];
        })
    ).subscribe(
      () => this._changeDetector.markForCheck()
    );
  }

  private getChartForRange(start: Date, end: Date) {
    this._service.getHistoryInRage(start, end).pipe(
      map(history => {
        this.barChartLabels = [];
        this.barChartData = [];

        history.days.map(r => {
          this.barChartLabels.push(this._datePipe.transform(new Date(r.date), 'dd/MM/yyyy')!);
          this.barChartData.push(
            {
              data: [r.consumed/3600000],
              label: 'consumed'
            }
            ,          
            {
              data: [r.charged/3600000],
              label: 'EV charged'
            },          
            {
              data: [r.generated /3600000],
              label: 'solar-panels'
            },
            {
              data: [r.imported/3600000],
              label: 'imported'
            },
            {
              data: [r.exported/3600000],
              label: 'exported'
            },
          );
        });
      })
    ).subscribe(
      () => this._changeDetector.markForCheck()
    );
  }

}
