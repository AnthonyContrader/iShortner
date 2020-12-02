import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { UserDTO } from 'src/dto/userdto';
import { Usertype } from 'src/dto/usertype';
import { UserService } from 'src/service/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  userDTO: UserDTO;
  err = false;
  
  constructor(private service: UserService, private router: Router) { }

  ngOnInit() {
    this.err = false;
  }

  register(f: NgForm): void{
    this.userDTO = new UserDTO();
    this.userDTO.username = f.value.username;
    this.userDTO.password = f.value.password;
    this.userDTO.usertype = Usertype.USER;
    this.service.register(this.userDTO).subscribe((res) => {this.err = false;  this.router.navigate(['/login']);} , (res) => {this.err = true} );
  }

  
}
