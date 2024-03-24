import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
//import { LandingComponent } from './landing/landing.component';
import { UserComponent } from './user.component';
import { LandingComponent } from './landing/landing.component';


@NgModule({
  declarations: [
    UserComponent,
    LandingComponent
  ],
  imports: [
    CommonModule,
    UserRoutingModule,
    // LandingComponent
  ]
})
export class UserModule { }
