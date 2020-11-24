import { UserInfoUrlComponent } from './user-info-url/user-info-url.component';
import { UserLayoutComponent } from './../layout/user-layout/user-layout.component';
import { UserDashboardComponent } from './user-dashboard/user-dashboard.component';
import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';

//Aggiungo al sotto path il component UserInfoUrlComponent

const routes: Routes = [
  {path:'user-dashboard', component: UserLayoutComponent, children: [
    {path: '', component: UserDashboardComponent},
    {path: 'user-info-url', component: UserInfoUrlComponent}
  ]}
]

@NgModule({
  imports: [ RouterModule.forChild(routes) ],
  exports: [RouterModule]
})
export class UserRoutingModule { }
