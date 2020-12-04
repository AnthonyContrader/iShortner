import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { UserDTO } from 'src/dto/userdto';
import { UserService } from 'src/service/user.service';

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.css']
})
export class UserEditComponent implements OnInit {

  username: string 
  password: string 
  dato: UserDTO

  constructor(private service: UserService) { }

  ngOnInit() {

    this.dato = JSON.parse(localStorage.getItem('currentUser'))
    this.username = this.dato.login
    this.password = this.dato.password
    
  }

  modifica(f: NgForm): void {
    this.dato.login = f.value.username;
    this.dato.password = f.value.password;
    this.service.update(this.dato).subscribe((res) => {localStorage.setItem('currentUser', JSON.stringify(res)); window.location.reload();});
  }

}
