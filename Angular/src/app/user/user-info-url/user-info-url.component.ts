import { UserDTO } from './../../../dto/userdto';
import { ServerService } from './../../../service/server.service';
import { UrlService } from './../../../service/url.service';
import { Component, OnInit } from '@angular/core';
import { ServerDTO } from 'src/dto/serverdto';
import { UrlDTO } from 'src/dto/urldto';

@Component({
  selector: 'user-info-url',
  templateUrl: './user-info-url.component.html',
  styleUrls: ['./user-info-url.component.css']
})


export class UserInfoUrlComponent implements OnInit {

  userDto : UserDTO;
  urlDto : UrlDTO[];
  empty : boolean = false;

  constructor(private serviceUrl: UrlService) { }

  ngOnInit() {
    this.getUrl();
  }

  getUrl(){
    this.userDto = JSON.parse(localStorage.getItem("currentUser"));
    this.serviceUrl.getUrlFromUser(this.userDto.id).subscribe((url) => {
      (url[0] != undefined) ? (
        this.urlDto = url
      ) : (
        this.empty = true
      )
      
    },
    () => {
      //Angela's Style
      console.warn("Errore Subscribe");
    });
  }

}