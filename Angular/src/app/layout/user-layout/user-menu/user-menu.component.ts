import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-menu',
  templateUrl: './user-menu.component.html',
  styleUrls: ['./user-menu.component.css']
})
export class UserMenuComponent implements OnInit {

  collapsed = false;

  constructor(private router: Router) { }

  ngOnInit() {
  }
  
  logout() {
    localStorage.clear();
    sessionStorage.clear();
    this.router.navigateByUrl('');
  }

  isCollapsed(){
    this.collapsed = !this.collapsed;
  }
}
