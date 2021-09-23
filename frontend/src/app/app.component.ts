import { Component, OnInit } from '@angular/core';
import { MyenergiService } from './myenergi.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'myenergi-frontend';

  public constructor(private readonly _myenergiService: MyenergiService) {}

  public ngOnInit(): void {
    this._myenergiService.getStatus().subscribe(r => console.log(r));
  }
}
