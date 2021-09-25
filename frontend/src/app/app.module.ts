import { DatePipe } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { APP_INITIALIZER, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ChartsModule } from 'ng2-charts';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ChartComponent } from './chart/chart.component';
import { Configuration } from './configuration';
import { MyenergiService } from './myenergi.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ReactiveFormsModule } from '@angular/forms';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field';
import { DateAdapter, MatNativeDateModule, MAT_DATE_LOCALE } from '@angular/material/core';
import { CustomDateAdapter } from './custom-date-adapter';

@NgModule({
  declarations: [
    AppComponent,
    ChartComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ChartsModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    MatDatepickerModule,
    MatFormFieldModule,
    MatNativeDateModule
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
