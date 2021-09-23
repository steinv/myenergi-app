import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';

const routes: Routes = [
  {
    path: '', component: AppComponent
  },
  {
    path: 'solar', component: AppComponent
  },
  {
    path: 'home', component: AppComponent
  },
  {
    path: 'car', component: AppComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
