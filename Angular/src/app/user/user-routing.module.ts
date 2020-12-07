import { EditComponent } from './edit/edit.component';
import { UserInfoUrlComponent } from './user-info-url/user-info-url.component';
import { UserLayoutComponent } from './../layout/user-layout/user-layout.component';
import { UserDashboardComponent } from './user-dashboard/user-dashboard.component';
import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { RoleGuardService } from 'src/service/role-guard.service';

//Aggiungo al sotto path il component UserInfoUrlComponent

const routes: Routes = [
  {path:'user-dashboard', component: UserLayoutComponent, canActivate:[RoleGuardService],  data: {  expectedRole: 'ROLE_USER'}, children: [
    {path: '', component: UserDashboardComponent},
    {path: 'edit', component: EditComponent},
    {path: 'user-info-url', component: UserInfoUrlComponent}
  ]}
]

@NgModule({
  imports: [ RouterModule.forChild(routes) ],
  exports: [RouterModule]
})
export class UserRoutingModule { }
