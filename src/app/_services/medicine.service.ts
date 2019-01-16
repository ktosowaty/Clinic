import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Medicine} from '../_models/medicine';
import {Doctor} from '../_models/doctor';


@Injectable({
  providedIn: 'root'
})
export class MedicineService {
  private medicineUrl = 'http://localhost:8088/medicine';

  constructor(private http: HttpClient) { }
  createMedicine(medicine: Medicine): Observable<HttpResponse<Medicine>> {
    const url = `${this.medicineUrl}/create`;

    return this.http.post<Medicine>(url,
      medicine,
      {observe: 'response'});
  }
  /** Get medicine from server */
  getMedicine(name: string): Observable<HttpResponse<Medicine>> {
    const url = `${this.medicineUrl}/${name}`;

    return this.http.get<Medicine>(url,
      { observe: 'response' });
  }
  getMedicineLOV(): Observable<HttpResponse<Medicine[]>> {
    const medicinesLOV = 'http://localhost:8088/medicine/all';
    return this.http.get<Medicine[]>(medicinesLOV,
      { observe: 'response' });
  }
}
