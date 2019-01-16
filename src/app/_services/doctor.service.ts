import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { Doctor} from '../_models/doctor';

@Injectable({
  providedIn: 'root'
})
export class DoctorService {

  constructor( private http: HttpClient) { }
  /** Get doctors LOV from the server */
  getDoctorsLOV(): Observable<HttpResponse<Doctor[]>> {
    const doctorsLOV = 'http://localhost:8088/doctor/all';
    return this.http.get<Doctor[]>(doctorsLOV,
      { observe: 'response' });
  }
}
