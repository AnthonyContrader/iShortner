import { Component, Input, OnInit } from '@angular/core';
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
    this.url.fk_url = this.user.id;
    this.url.longurl = f.value.longurl,
    this.service.create(this.url).subscribe((res) => {
      (res == null)? (
        this.err = true
      ):( 
        this.err = false, this.url = res 
      )})
  }

}
