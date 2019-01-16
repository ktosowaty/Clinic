import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import {History} from '../_models/history';
import {Medicine} from '../_models/medicine';
import {Patient} from '../_models/patient';

@Injectable({
  providedIn: 'root'
})
export class PatientHistoryService {
  private historyPatientOwnUrl = 'http://localhost:8088/history/patient/own';
  private historyPatientUrl = 'http://localhost:8088/history/patient';
  private createHistoryUrl = 'http://localhost:8088/history';
  constructor( private http: HttpClient) { }

  /** Get patient history from the server own*/
  getOwnHistory(): Observable<HttpResponse<History[]>> {
    return this.http.get<History[]>(this.historyPatientOwnUrl,
      { observe: 'response' });
  }

  /** Get patient history by doctor from the server */
  getPatientHistory(pesel: string): Observable<HttpResponse<History[]>> {
    const url = `${this.historyPatientUrl}/${pesel}`;

    return this.http.get<History[]>(url,
      { observe: 'response' });
  }
  createHistory(history: History): Observable<HttpResponse<History>> {
    const url = `${this.createHistoryUrl}/create-record`;

    return this.http.post<History>(url,
      history,
      {observe: 'response'});
  }
  getPatientsLOV(): Observable<HttpResponse<Patient[]>> {
    const patientsLOV = 'http://localhost:8088/patient/all';
    return this.http.get<Patient[]>(patientsLOV,
      { observe: 'response' });
  }
}


