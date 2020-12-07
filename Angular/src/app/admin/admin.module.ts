import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AdminRoutingModule } from './admin-routing.module';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { UsersComponent } from './users/users.component';
import { StatsComponent } from './stats/stats.component';
import { InfoUrlComponent } from './info-url/info-url.component';
import { AuthService } from 'src/service/auth.service';


/**
 * Modulo dell'admin, qui vengono dichiarate le component che utilizza 
 * l'admin. Questo modulo importa AdminRoutingModule.
 * 
 * @author Vittorio Valent
 *  
 * @see AdminRoutingModule
 */
@NgModule({
  declarations: [AdminDashboardComponent, UsersComponent, StatsComponent, InfoUrlComponent],
  imports: [
    CommonModule,
    AdminRoutingModule,
    FormsModule
  ],
  providers:[AuthService]
})
export class AdminModule { }
