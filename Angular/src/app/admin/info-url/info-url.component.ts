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
  }

  getUserInfo(id: number){
    this.service.getUrlFromUser(id).subscribe((res) => {
      res==null? 
        this.noData = true 
        :
        this.url = res; 
        this.url.map((el, index) => {
          this.serv.getInfoUrl(el.id).subscribe((res) => this.server.push(res));
          if(this.url.length - 1  === index){
            this.server.sort((a, b) => a.id - b.id); // non funziona
            this.loading = true;
          }
        });
        
    });  
 }
}
