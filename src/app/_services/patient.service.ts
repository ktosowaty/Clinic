import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Patient} from '../_models/patient';
import { Observable, of } from 'rxjs';
import {Disease} from "../_models/disease";


@Injectable({
  providedIn: 'root'
})
export class PatientService {
  private patientUrl  = 'http://localhost:8088/patient/pesel';
  constructor(private http: HttpClient) { }

  getPatient(peselStr: string): Observable<HttpResponse<Patient>> {
    const url = `${this.patientUrl}/${peselStr}`;
    return this.http.get<Patient>(url,

      { observe: 'response' });

  }

}
