import { DatePipe } from '@angular/common';
import { ChangeDetectionStrategy, ChangeDetectorRef, Component, OnInit } from '@angular/core';
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
export class ChartComponent implements OnInit {

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

  public ngOnInit(): void {
    // TODO allow client to choose period of history
    const d = new Date(Date.now() - (24 * 60 * 60 * 1000));

    this._service.getHistory(d).pipe(
      map(history => {;
        this.barChartLabels = [this._datePipe.transform(d, 'dd/MM/yyyy')!];
        this.barChartData = [
          {
            data: [history.consumed/3600000],
            label: 'consumed'
          }
          ,          
          {
            data: [history.charged/3600000],
            label: 'EV charged'
          },          
          {
            data: [history.generated /3600000],
            label: 'solar-panels'
          },
          {
            data: [history.imported/3600000],
            label: 'imported'
          },
          {
            data: [history.exported/3600000],
            label: 'exported'
          },
        ];
      })
    ).subscribe(
      () => this._changeDetector.markForCheck()
    );
  }


}
