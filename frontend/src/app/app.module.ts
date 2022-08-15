import { DatePipe } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { APP_INITIALIZER, NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { DateAdapter, MatNativeDateModule, MAT_DATE_LOCALE } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgChartsModule } from 'ng2-charts';
import { AppComponent } from './app.component';
import { ChartComponent } from './chart/chart.component';
import { Configuration } from './configuration';
import { CustomDateAdapter } from './custom-date-adapter';
import { MyenergiService } from './myenergi.service';

@NgModule({
  declarations: [
    AppComponent,
    ChartComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatDatepickerModule,
    MatFormFieldModule,
    MatNativeDateModule,
    NgChartsModule,
  ],
  providers: [
    MyenergiService,
    DatePipe, 
    Configuration,
    {
      provide: DateAdapter, 
      useClass: CustomDateAdapter 
    },
    { 
      provide: MAT_DATE_LOCALE, 
      useValue:  'en-UK', 
    },   
    {
      provide: APP_INITIALIZER,
      multi: true,
      deps: [Configuration],
      useFactory: (config: Configuration) => () => config.load('./assets/config.json')
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
