import { StatsDTO } from './../../../dto/stats';
import { ServerDTO } from './../../../dto/serverdto';
import { UrlDTO } from './../../../dto/urldto';
import { ServerService } from './../../../service/server.service';
import { UrlService } from 'src/service/url.service';
import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/service/user.service';
import { UserDTO } from 'src/dto/userdto';
import { StatsService } from 'src/service/stats.service';

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
  stats: StatsDTO[];
 
  constructor(private service: UserService, private serv: UrlService, private servServer: ServerService, private stat: StatsService) { }

  ngOnInit() {
    this.getUsers();
    this.getStats();
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
    this.service.insert(user).subscribe((res) => {res == null ? console.log("User esistente"): this.getUsers()});
  }

  getStats(){
    this.stat.getStats().subscribe((res) => this.stats = res);
  }

  clear(){
    this.usertoinsert = new UserDTO();
  }
}
