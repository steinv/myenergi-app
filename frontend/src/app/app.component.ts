import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  private date = new Date();
  public dateRange = new FormGroup({
    start: new FormControl(this.date),
    end: new FormControl(this.date)
  });
  
  public constructor() {}

  public ngOnInit(): void {
    this.dateRange.controls['start'].value;
  }
}
