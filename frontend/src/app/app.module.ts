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

@NgModule({
  declarations: [
    AppComponent,
    ChartComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ChartsModule,
  ],
  providers: [
    MyenergiService,
    DatePipe, 
    Configuration,     
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
