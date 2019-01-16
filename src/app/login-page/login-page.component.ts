import { Component, OnInit } from '@angular/core';
import { ErrorMessage} from 'ng-bootstrap-form-validation';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

import {AuthenticationService} from '../_services/authentication.service';
import {first} from 'rxjs/operators';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {
  loginFormGroup: FormGroup;
  returnUrl: string;
  customErrorMessages: ErrorMessage[] = [
    {
      error: 'required',
      format: (label, error) => `Zawartość pola "${label}" nie może być pusta!`
    }
  ];
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService,
    private toastr: ToastrService,
  ) {

  }

  ngOnInit() {
    this.loginFormGroup = new FormGroup({
      username: new FormControl('', [
        Validators.required
      ]),
      password: new FormControl('', [
        Validators.required
      ]),
    });

    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/main-page';
  }

  onSubmit() {
    const username = this.loginFormGroup.controls.username.value;
    const password = this.loginFormGroup.controls.password.value;
    this.authenticationService.login(username, password)
      .pipe(first())
      .subscribe(
        data => {
          this.toastr.success('Zalogowano do aplikacji');
          this.router.navigate([this.returnUrl]);
        },
        error => {
          this.toastr.error('Niepoprawne dane logowania');
        });
  }

}
