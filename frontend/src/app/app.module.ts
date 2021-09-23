import { APP_INITIALIZER, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {MatIconModule} from '@angular/material/icon';
import {MatCardModule} from '@angular/material/card';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MyenergiService } from './myenergi.service';
import { Configuration } from './configuration';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    MatIconModule,
    MatCardModule
  ],
  providers: [
    MyenergiService, 
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
