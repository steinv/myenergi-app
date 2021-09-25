import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  public dateRange = new FormGroup({
    start: new FormControl(new Date()),
    end: new FormControl(new Date())
  });
  
  public constructor() {}

  public ngOnInit(): void {
    this.dateRange.controls['start'].value;
  }
}
