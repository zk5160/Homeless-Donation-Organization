import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import { LandingComponent } from './landing/landing.component';
import { UserComponent } from './user.component';


@NgModule({
  declarations: [
    UserComponent,
    LandingComponent
  ],
  imports: [
    CommonModule,
    UserRoutingModule,
  ]
})
export class UserModule { }
