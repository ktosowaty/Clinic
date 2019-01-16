import { Injectable } from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {Observable} from 'rxjs';
import { User } from '../_models/user';
import { HttpClient} from '@angular/common/http';
import {map} from 'rxjs/operators';
import {default as decode} from 'jwt-decode';
import { JwtHelperService } from '@auth0/angular-jwt';
@Injectable({ providedIn: 'root' })
export class AuthenticationService {

  constructor(private http: HttpClient) {
    this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('currentUser')));
    this.currentUser = this.currentUserSubject.asObservable();
  }
 // const helper = new JwtHelperService();
  public get currentUserValue(): User {
    return this.currentUserSubject.value;
  }
  private currentUserSubject: BehaviorSubject<User>;
  public currentUser: Observable<User>;


  login(username: string, password: string) {
    return this.http.post<any>('http://localhost:8088/public/auth/login', {username, password})
      .pipe(map(user => {
        // login successful if there's a jwt token in the response
        if (user && user.token) {
          // store user details and jwt token in local storage to keep user logged in between page refreshes
          localStorage.setItem('currentUser', JSON.stringify(user));
          console.log(user);
          console.log(user.email);
          console.log(user.username);


          this.currentUserSubject.next(user);

        }

        return user;
      }));

  }

  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
  }
}
