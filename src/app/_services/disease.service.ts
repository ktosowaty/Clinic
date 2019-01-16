import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Disease } from '../_models/disease';




@Injectable({
  providedIn: 'root'
})
export class DiseaseService {
  private diseaseUrl = 'http://localhost:8088/disease';

  constructor(private http: HttpClient) { }
  createDisease(disease: Disease): Observable<HttpResponse<Disease>> {
    const url = `${this.diseaseUrl}/create`;

    return this.http.post<Disease>(url,
      disease,
      {observe: 'response'});
  }
  /** Get disease from server */
  getDisease(name: string): Observable<HttpResponse<Disease>> {
    const url = `${this.diseaseUrl}/${name}`;

    return this.http.get<Disease>(url,
      { observe: 'response' });
  }
  getDiseaseLOV(): Observable<HttpResponse<Disease[]>> {
    const diseasesLOV = 'http://localhost:8088/disease/all';
    return this.http.get<Disease[]>(diseasesLOV,
      { observe: 'response' });
  }
}

