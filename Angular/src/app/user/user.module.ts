import { EditComponent } from './edit/edit.component';
import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserDashboardComponent } from './user-dashboard/user-dashboard.component';
import { UserRoutingModule } from './user-routing.module';
import { UserInfoUrlComponent } from './user-info-url/user-info-url.component';
import { AuthService } from 'src/service/auth.service';

@NgModule({
  declarations: [UserDashboardComponent, EditComponent, UserInfoUrlComponent],
  imports: [
    CommonModule,
    UserRoutingModule,
    FormsModule
  ],
  providers:[ AuthService]
})
export class UserModule { }
