import { UserDTO } from './../../dto/userdto';
import { Component, OnInit } from '@angular/core';
import { LoginDTO } from 'src/dto/logindto';
import { NgForm } from '@angular/forms';
import { UserService } from 'src/service/user.service';
import { Router } from '@angular/router';
import { Usertype } from 'src/dto/usertype';
import { stripGeneratedFileSuffix } from '@angular/compiler/src/aot/util';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginDTO: LoginDTO;
  userDTO: UserDTO = new UserDTO();
  err = false;

  constructor(private service: UserService, private router: Router) { }

  ngOnInit() {
    this.err = false;
  }

 

  login(f: NgForm): void {
    this.loginDTO = new LoginDTO(f.value.username.toLowerCase(), f.value.password);
    this.service.authenticate(this.loginDTO).subscribe((res) => {  
      sessionStorage.setItem("id_token", res.id_token);
      this.userDTO.login = this.loginDTO.username;
      this.userDTO.password = this.loginDTO.password;
      this.service.login(this.userDTO).subscribe((user) => {
      
        if (user != null) {
          localStorage.setItem("currentUser", JSON.stringify(user));
     
          switch (user.usertype.toString()) {
            case 'ADMIN': {
              this.router.navigate(['/admin-dashboard']);
              break;
            }
            case 'USER': {
              this.router.navigate(['/user-dashboard']);
              break;
            }
            default:
              this.router.navigate(['/login']);
          }
        }else {this.err = true}
      }, () => {sessionStorage.clear(); localStorage.clear();});   
    })}
}
