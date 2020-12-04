import { ThrowStmt } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { UserDTO } from 'src/dto/userdto';
import { UserService } from 'src/service/user.service';

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent implements OnInit {

  username: string 
  password: string 
  dato: UserDTO
  err = false;

  constructor(private service: UserService) { }

  ngOnInit() {
    this.dato = JSON.parse(localStorage.getItem('currentUser'))
    this.username = this.dato.login
    this.password = this.dato.password
    this.err = false;
  }

  edit(f: NgForm): void{
    this.dato.login = f.value.username
    this.dato.password = f.value.password
    this.service.update(this.dato).subscribe((res) => {localStorage.setItem('currentUser', JSON.stringify(res)); window.location.reload()}, 
    (res) => {this.err = true;}); 
  }


}
