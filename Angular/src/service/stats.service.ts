import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StatsDTO } from 'src/dto/stats';
import { AbstractService } from 'src/service/abstractservice';

@Injectable({
  providedIn: 'root'
})
export class StatsService extends AbstractService<StatsDTO> {

  constructor(protected http: HttpClient) {
    super(http);
    this.type = 'user';
  }

  getStats(): Observable<StatsDTO[]> {
    return this.http.get<StatsDTO[]>('http://localhost:' + this.port + '/services/stats/api/stats/getcount');
  }

  insertStats(statsDto: StatsDTO):Observable<StatsDTO>{
    return this.http.post<StatsDTO>('http://localhost:8080/services/stats/api/stats/insert', statsDto);
  }
}
