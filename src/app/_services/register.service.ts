import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import {Person} from '../_models/person';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {
  private userRegister = 'http://localhost:8088/user/register';

  constructor(private http: HttpClient) { }
  createPerson(person: Person): Observable<HttpResponse<Person>> {

    return this.http.post<Person>(this.userRegister,
      person,
      {observe: 'response'});
  }
}
