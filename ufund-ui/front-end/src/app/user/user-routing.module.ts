import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserComponent } from './user.component';
import { LandingComponent } from './landing/landing.component';

const routes: Routes = [
  {
    path: '', 
    component: UserComponent, 
    children: [{path:'', component: LandingComponent}]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
