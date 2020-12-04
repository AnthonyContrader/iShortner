import { StatsDTO } from './../../../dto/stats';
import { ServerDTO } from './../../../dto/serverdto';
import { UrlDTO } from './../../../dto/urldto';
import { ServerService } from './../../../service/server.service';
import { UrlService } from 'src/service/url.service';
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
  url: UrlDTO[];
  server: ServerDTO[];
  err = false;

  constructor(private service: UserService, private serv: UrlService, private servServer: ServerService) { }

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
    this.service.update(user).subscribe(() => {this.getUsers()});
  }

  insert(user: UserDTO) {
    if(user.login === undefined || user.password === undefined || user.usertype === undefined) {
      this.err = true;
    } else {
      this.service.insert(user).subscribe((res) => {
      (res == null)? (
          console.warn("User esistente"),
          this.err = true
        ):( 
          this.err = false,
          this.getUsers()
        )
      });
    }
  }

  clear(){
    this.usertoinsert = new UserDTO();
  }
}
