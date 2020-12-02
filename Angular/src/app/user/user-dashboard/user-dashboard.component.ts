import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { UrlService } from 'src/service/url.service';
import { UserDTO } from 'src/dto/userdto';
import { UrlDTO } from 'src/dto/urldto';
import { NgForm } from '@angular/forms';

/**
 * Componente della dashboard utente
 */
@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.css']
})
export class UserDashboardComponent implements OnInit {

  user: UserDTO;
  url: UrlDTO = new UrlDTO();
  err = false;
  
  @Input() pattern: string | RegExp

  constructor(private service: UrlService) { }

  //recupera l'utente loggato
  ngOnInit() {
    this.user = JSON.parse(localStorage.getItem('currentUser'));
  }

  getUrl(f: NgForm): void {
    this.url.fkurl = this.user.id;
    this.url.longurl = f.value.longurl;
    console.log(this.url.longurl)
    this.service.create(this.url).subscribe((res) => {
      (res == null)? (
        this.err = true
      ):( 
        this.err = false, this.url = res 
      )})
  }

  redirect(g: NgForm): void{
    console.log(this.url.longurl)
    this.service.redirect(this.url).subscribe((url) => this.url = url);
    window.location.href = this.url.longurl
  }
  
}
