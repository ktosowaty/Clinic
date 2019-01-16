import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Opinion} from '../_models/opinion';

@Injectable({
  providedIn: 'root'
})
export class OpinionService {
  private getOpinionUrl = 'http://localhost:8088/opinion/doctor';
  private createOpinionUrl = 'http://localhost:8088/opinion';
  constructor(private http: HttpClient) { }
  /** Get disease from server */
  getOpinion(name: string): Observable<HttpResponse<Opinion>> {
    const url = `${this.getOpinionUrl}/${name}`;

    return this.http.get<Opinion>(url,
      { observe: 'response' });

  }

  createOpinion(opinion: Opinion): Observable<HttpResponse<Opinion>> {
    const url = `${this.createOpinionUrl}/create`;

    return this.http.post<Opinion>(url,
      opinion,
      {observe: 'response'});
  }

}

