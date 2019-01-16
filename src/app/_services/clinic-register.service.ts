import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Patient} from '../_models/patient';

@Injectable({
  providedIn: 'root'
})
export class ClinicRegisterService {
  private getPatientPesel = 'http://localhost:8088/patient/pesel';
  private getPatientName = 'http://localhost:8088/patient/name';
  private registerSecretary = 'http://localhost:8088/patient/register/secretary';
  private registerOwn = 'http://localhost:8088/patient/register/own';
  constructor(private http: HttpClient) { }
  getPatientByPesel(pesel: string): Observable<HttpResponse<Patient>> {
    const url = `${this.getPatientPesel}/${pesel}`;

    return this.http.get<Patient>(url,
      { observe: 'response' });

  }
  getPatientByName(name: string): Observable<HttpResponse<Patient>> {
    const url = `${this.getPatientName}/${name}`;

    return this.http.get<Patient>(url,
      { observe: 'response' });

  }

  postPatientBySecretary(patient: Patient): Observable<HttpResponse<Patient>> {
    const url = `${this.registerSecretary}`;

    return this.http.post<Patient>(url,
      patient,
      {observe: 'response'});
  }
  postPatientByHimself(patient: Patient): Observable<HttpResponse<Patient>> {
    const url = `${this.registerOwn}`;

    return this.http.post<Patient>(url,
      patient,
      {observe: 'response'});
  }

}
