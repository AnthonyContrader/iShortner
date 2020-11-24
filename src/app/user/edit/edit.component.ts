import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { UserDTO } from 'src/dto/userdto';
import { dashCaseToCamelCase } from '@angular/compiler/src/util';
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

  constructor(private service: UserService) { }

  ngOnInit() {
    this.dato = JSON.parse(localStorage.getItem('currentUser'))
    this.username = this.dato.username
    this.password = this.dato.password
  }

  edit(f: NgForm): void{
    this.dato.username = f.value.username
    this.dato.password = f.value.password
    this.service.update(this.dato).subscribe((res) => {localStorage.setItem('currentUser', JSON.stringify(res)); window.location.reload()}); 
  }


}
