import { NodeWithI18n } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';

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
  
  public constructor() {}

  public ngOnInit(): void {
    this.dateRange.controls['start'].value;
  }

  public today(): void {
    this.dateRange.patchValue({start: this.now, end: this.now});
  }
}
