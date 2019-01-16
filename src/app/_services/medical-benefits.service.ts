import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import {Package} from '../_models/packages';

@Injectable({
  providedIn: 'root'
})
export class MedicalBenefitsService {
  private packagesDisplayUrl = 'http://localhost:8088/package/all';
  private packagesPurchaseUrl = 'http://localhost:8088/package/purchase';

  constructor(private http: HttpClient) {
  }

  /** Get packages from the server */
  getPackages(): Observable<HttpResponse<Package[]>> {
    return this.http.get<Package[]>(this.packagesDisplayUrl,
      {observe: 'response'});
  }

  purchasePackage(benefitPackageId: number): Observable<HttpResponse<any>> {
    return this.http.post<Package>(this.packagesPurchaseUrl,
      benefitPackageId,
      {observe: 'response'});
  }
}
