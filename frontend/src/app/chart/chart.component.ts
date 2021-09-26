import { DatePipe, registerLocaleData } from '@angular/common';
import { ChangeDetectionStrategy, ChangeDetectorRef, Component, Input, OnChanges } from '@angular/core';
import { ChartType, ChartOptions, ChartDataSets } from 'chart.js';
import { Label } from 'ng2-charts';
import { map, tap } from 'rxjs/operators';
import { DayCall, MyenergiService } from '../myenergi.service';

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

  private consumed: ChartDataSets = {
    label: 'consumed',
    data: [],
  };
  private evCharged: ChartDataSets = {
    data: [],
    label: 'EV charged'
  };         
  private solarPanels: ChartDataSets = {
    data: [],
    label: 'solar-panels'
  };
  private imported: ChartDataSets =  {
    data: [],
    label: 'imported'
  };
  private exported: ChartDataSets = {
    data: [],
    label: 'exported'
  };

  public barChartOptions: ChartOptions = {
    responsive: true,
  };
  public barChartLabels: Label[] = [];
  public barChartType: ChartType = 'bar';
  public barChartLegend = true;
  public barChartPlugins = [];
  public barChartData: ChartDataSets[] = [this.consumed, this.evCharged, this.solarPanels, this.imported, this.exported];
  
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
      tap(() => this.resetData()),
      map(history => this.populateData([history]))
    ).subscribe(
      () => this._changeDetector.markForCheck()
    );
  }

  private getChartForRange(start: Date, end: Date) {
    this._service.getHistoryInRage(start, end).pipe(
      tap(() => this.resetData()),
      map(history => this.populateData(history.days))
    ).subscribe(
      () => this._changeDetector.markForCheck()
    );
  }

  /** reset back to empty data sets */
  private resetData() {
    this.barChartLabels = [];
    this.consumed.data = [];
    this.evCharged.data = [];
    this.solarPanels.data = [];
    this.imported.data = [];
    this.exported.data = [];
  }

  /** populate data sets with received information */
  private populateData(days: Array<DayCall>) {
    days.map(r => {
      this.barChartLabels.push(this._datePipe.transform(new Date(r.date), 'dd/MM/yyyy')!);
      this.consumed.data?.push(r.consumed / 3600000);
      this.evCharged.data?.push(r.charged / 3600000);
      this.solarPanels.data?.push(r.generated / 3600000);
      this.imported.data?.push(r.imported / 3600000);
      this.exported.data?.push(r.exported / 3600000);
    });
  }

}
