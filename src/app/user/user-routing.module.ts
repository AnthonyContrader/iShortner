import { UserLayoutComponent } from './../layout/user-layout/user-layout.component';
import { UserDashboardComponent } from './user-dashboard/user-dashboard.component';
import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';


const routes: Routes = [
  {path:'user-dashboard', component: UserLayoutComponent, children: [
    {path:'', component: UserDashboardComponent}
  ]}
]

@NgModule({
  imports: [ RouterModule.forChild(routes) ],
  exports: [RouterModule]
})
export class UserRoutingModule { }
