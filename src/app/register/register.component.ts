import { Component, } from '@angular/core';
import { ErrorMessage} from 'ng-bootstrap-form-validation';
import { OnInit} from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import {AuthenticationService} from '../_services/authentication.service';
import {first} from 'rxjs/operators';
import { ToastrService } from 'ngx-toastr';
import {RegisterService} from '../_services/register.service';
import {Person } from '../_models/person';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  types = [
    {id: 'PATIENT', name: 'Pacjent'},

  ];
  selectedTypeId: string;
  registerForm: FormGroup;
  returnUrl: string;
  customErrorMessages: ErrorMessage[] = [
    {
      error: 'required',
      format: (label, error) => `Zawartość pola "${label}" nie może być pusta!`
    }
  ];
  constructor(    private route: ActivatedRoute,
                  private router: Router,
                  private authenticationService: AuthenticationService,
                  private registerService: RegisterService,
                  private toastr: ToastrService, ) {
    if (this.authenticationService.currentUserValue) {
      this.router.navigate(['/']);
    }
  }


  ngOnInit() {
    this.registerForm = new FormGroup({
      username: new FormControl('', [
        Validators.required
      ]),
      plainPassword: new FormControl('', [
        Validators.required
      ]),
      email: new FormControl('', [
        Validators.required
      ]),
      userType: new FormControl('', [
        Validators.required
      ]),
    });

    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/login';
  }

  onSubmit() {

    this.registerService.createPerson(this.registerForm.value)
      .pipe(first())
      .subscribe(
        data => {
          this.toastr.success('Pomyślnie utworzono użytkownika');
          this.router.navigate([this.returnUrl]);
        },
        error => {
          this.toastr.error('Niepoprawne wartości rejestracji');
        });
  }

}
