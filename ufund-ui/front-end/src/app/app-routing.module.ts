import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { DashboardComponent } from './dashboard/dashboard.component';
import { NeedsComponent } from './needs/needs.component';
import { NeedDetailComponent } from './need-detail/need-detail.component';
import { UserDetailComponent } from './userdetail/userdetail.component';
import { LoginComponent } from './login/login.component';
import { FundingBasketComponent } from './funding-basket/funding-basket.component';

const routes: Routes = [
  { path: '', loadChildren: ()=>import('./user/user.module').then((m)=>m.UserModule)}, 
  { path: 'admin', loadChildren: ()=>import('./admin/admin.module').then((m)=>m.AdminModule)}, 
  { path: 'login', component: LoginComponent},
  { path: 'logout', redirectTo: '', pathMatch: 'full'},
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'detail/:id', component: NeedDetailComponent },
  { path: 'userdetail/:id', component: UserDetailComponent },
  { path: 'needs', component: NeedsComponent },
  { path: 'funding-basket', component: FundingBasketComponent }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}