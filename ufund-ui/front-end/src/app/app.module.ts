import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { NeedDetailComponent } from './need-detail/need-detail.component';
import { UserDetailComponent } from './userdetail/userdetail.component';
import { NeedsComponent } from './needs/needs.component';
import { NeedSearchComponent } from './need-search/need-search.component';
import { MessagesComponent } from './messages/messages.component';
import { FundingBasketComponent } from './funding-basket/funding-basket.component';
//import { LandingComponent } from './user/landing/landing.component';
import { LoginComponent } from './login/login.component';

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  declarations: [
    AppComponent,
    UserDetailComponent,
    DashboardComponent,
    NeedsComponent,
    NeedDetailComponent,
    MessagesComponent,
    NeedSearchComponent,
    FundingBasketComponent,
    //LandingComponent,
    LoginComponent
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }