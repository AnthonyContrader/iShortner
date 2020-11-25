import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/service/user.service';
import { UserDTO } from 'src/dto/userdto';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {
  users: UserDTO[];
  usertoinsert: UserDTO = new UserDTO();
 
  constructor(private service: UserService, private router : Router) { }

  ngOnInit() {
    this.getUsers();
  }

  getUsers() {
    this.service.getAll().subscribe(users => this.users = users);
  }

  delete(user: UserDTO) {
    this.service.delete(user.id).subscribe(() => this.getUsers());
  }

  update(user: UserDTO) {
    this.service.update(user).subscribe(() => {console.log("sda"); this.getUsers()});
  }

  insert(user: UserDTO) {
    this.service.insert(user).subscribe((res) => {res == null ? console.log("User esistente"): this.getUsers()});
  }

  
  visualizza(){
    
  }

  clear(){
    this.usertoinsert = new UserDTO();
  }
}
