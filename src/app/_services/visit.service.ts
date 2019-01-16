import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Avabilities } from '../_models/avabilities';
import { Visit} from '../_models/visit';

@Injectable({
  providedIn: 'root'
})
export class VisitService {
  private visitDoctorUrl = 'http://localhost:8088/visit/doctor';
  private visitSpecializationUrl = 'http://localhost:8088/visit/doctor/specialization';
  private visitReservationSecretary = 'http://localhost:8088/visit/secretary/reservation';
  private visitReservationOwn = 'http://localhost:8088/visit/reservation';

  constructor(private http: HttpClient) { }

  /** Get available doctor from the server */
  getDoctorAvabilities(name: string): Observable<HttpResponse<Avabilities[]>> {
    const url = `${this.visitDoctorUrl}/${name}`;
    return this.http.get<Avabilities[]>(url,
      { observe: 'response' });
  }

  /** Get available specialization from the server */
  getSpecializationAvabilities(specialization: string): Observable<HttpResponse<Avabilities[]>> {
    const url = `${this.visitSpecializationUrl}/${specialization}`;
    return this.http.get<Avabilities[]>(url,
      { observe: 'response' });
  }
  createVisitBySecretary(patientId: number, doctorId: number, visitStartStr: string): Observable<HttpResponse<Visit>> {
    return this.http.post<Visit>(this.visitReservationSecretary,
      {patientId, doctorId, visitStartStr},
      {observe: 'response'});
  }
  createVisitOwn( doctorId: number, visitStartStr: string ): Observable<HttpResponse<Visit>> {
    return this.http.post<Visit>(this.visitReservationOwn,
      {doctorId, visitStartStr},
      {observe: 'response'});
  }
}

