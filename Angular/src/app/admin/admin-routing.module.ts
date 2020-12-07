import { ErrorRoleComponent } from './../error-role/error-role.component';
import { ErrorComponent } from './../error/error.component';
import { RoleGuardService } from './../../service/role-guard.service';
import { UserInfoUrlComponent } from './../user/user-info-url/user-info-url.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminLayoutComponent } from '../layout/admin-layout/admin-layout.component';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { UsersComponent } from './users/users.component';
import { StatsComponent } from './stats/stats.component';
import { InfoUrlComponent } from './info-url/info-url.component';

/**
 * Modulo di routing dell'admin. Qui ci sono i percorsi che un admin può seguire:
 * appena fa il login viene caricato nel <router-outlet> di app-component il layout e nel 
 * <router-outlet> del layout (come percorsi "children") vengono visualizzati gli altri 
 * (qui sotto sono indentati).
 * 
 * @author Vittorio Valent
 * 
 * @see AdminLayoutComponent
 * 
 * @see layout
 */
const routes: Routes = [
  { path: 'admin-dashboard', component: AdminLayoutComponent, canActivate:[RoleGuardService],  data: {  expectedRole: 'ROLE_ADMIN,ROLE_USER'  } , children:[
    { path: '', component: AdminDashboardComponent},
    { path: 'users', component: UsersComponent},
    { path: 'stats', component: StatsComponent},
    { path: 'info-url/:id', component: InfoUrlComponent }
  ]},
  //{ path: 'error-role', component: ErrorRoleComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }