import { ServerDTO } from '../../../dto/serverdto';
import { UrlDTO } from '../../../dto/urldto';
import { ServerService } from '../../../service/server.service';
import { UrlService } from 'src/service/url.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-info-url',
  templateUrl: './info-url.component.html',
  styleUrls: ['./info-url.component.css']
})
export class InfoUrlComponent implements OnInit {


  id: number;
  url: UrlDTO[];
  server: ServerDTO[] = [];
  noData = false;
  loading = false;

  constructor(private route: ActivatedRoute, private service: UrlService, private serv: ServerService) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.id = +params['id']; 
   });
   this.getUserInfo(this.id);
   this.loading = false;
   this.noData = false;
  }

  getUserInfo(id: number){
    this.service.getUrlFromUser(id).subscribe((res) => {
      if(res.length == 0){
        this.loading = true;
        this.noData = true
      }else {
        this.url = res 
        this.url.map((el) => {
          this.serv.getInfoUrl(el.id).subscribe((res) => this.server.push(res),
          () => console.warn("errore"), 
          () => {if(this.url.length == this.server.length){this.sort(); this.loading = true}}
          );
        })
      };
    });  
 }

 sort(){
   this.server.sort((a,b) => {return a.id - b.id} )
 }
}
