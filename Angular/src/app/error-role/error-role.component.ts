import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-error-role',
  templateUrl: './error-role.component.html',
  styleUrls: ['./error-role.component.css']
})
export class ErrorRoleComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  returnBack(){
    window.history.back();
  }

}
