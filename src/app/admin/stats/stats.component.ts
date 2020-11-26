import { Component, OnInit } from '@angular/core';
import { StatsDTO } from 'src/dto/stats';
import { StatsService } from 'src/service/stats.service';

@Component({
  selector: 'app-stats',
  templateUrl: './stats.component.html',
  styleUrls: ['./stats.component.css']
})
export class StatsComponent implements OnInit {

  stats: StatsDTO[];

  constructor(private stat: StatsService) { }

  ngOnInit() {
    this.getStats();
  }

  getStats(){
    this.stat.getStats().subscribe((res) => this.stats = res);
  }

}
