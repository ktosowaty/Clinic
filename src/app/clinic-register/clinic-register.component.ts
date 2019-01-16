import { Component, OnInit } from '@angular/core';
import {Patient} from '../_models/patient';
import { ClinicRegisterService} from '../_services/clinic-register.service';
import {ErrorMessage} from 'ng-bootstrap-form-validation';
import { ToastrService } from 'ngx-toastr';
import { ActivatedRoute } from '@angular/router';
import { NgbCalendar } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import { User} from '../_models/user';

declare var $: any;
@Component({
  selector: 'app-clinic-register',
  templateUrl: './clinic-register.component.html',
  styleUrls: ['./clinic-register.component.css']
})
export class ClinicRegisterComponent implements OnInit {
  gender = [
    {id: 'MALE', name: 'Mężczyzna'},
    {id: 'FEMALE', name: 'Kobieta'},

  ];

  province = [
    {id: 'MAZOWIECKIE', name: 'Mazowieckie'},
    {id: 'LUBELSKIE', name: 'Lubelskie'},
    {id: 'WIELKOPOLSKIE', name: 'Wielkopolskie'},
    {id: 'PODLASKIE', name: 'Podlaskie'},
  ];
  selectedGenderId: string;
  selectedProvinceId: string;
  formGroup: FormGroup;
  formGroupModal: FormGroup;

  record: Patient;
  customErrorMessages: ErrorMessage[] = [
    {
      error: 'required',
      format: (label, error) => `Zawartość pola "${label}" nie może być pusta!`
    }];
  constructor(
    private route: ActivatedRoute,
    private clinicService: ClinicRegisterService,
    private calendar: NgbCalendar,
    private toastr: ToastrService
  ) { }

  ngOnInit() {
    $(document).ready(function() {
      const navLink = $('.nav-link')[1];
      $('.nav-link').each(function(this) {
        $(this).removeClass('active');
      });
      $(navLink).addClass('active');
    });

    this.formGroupModal = new FormGroup({
      firstName: new FormControl('', [
        Validators.required
      ]),
      surname: new FormControl('', [
        Validators.required
      ]),
      secondName: new FormControl('', [
        Validators.required
      ]),
      gender: new FormControl('', [
        Validators.required
      ]),
      birthDateStr: new FormControl('', [
        Validators.required
      ]),
      peselStr: new FormControl('', [
        Validators.required
      ]),
     street: new FormControl('', [
        Validators.required
      ]),
      zipCodeStr: new FormControl('', [
        Validators.required
      ]),
      city: new FormControl('', [
        Validators.required
      ]),
      provinceStr: new FormControl('', [
        Validators.required
      ]),
      phoneNumberStr: new FormControl('', [
        Validators.required
      ])
    });
  }
  createPatient(): void {
   // this.prepareForm();
    this.clinicService.postPatientByHimself(this.formGroupModal.value)
      .subscribe(resp => {
        $('#createPatient').modal('toggle');
        this.toastr.success('Poprawnie zarejestrowano w przychodni');
      }, error => {
        this.toastr.error('Wystąpił błąd podczas rejestracji. Sprawdź swoje dane');
      });
  }
  // prepareForm(): void {
  //   this.record = this.formGroupModal.value;
  //
  //   const birthDateStrJSON = this.formGroupModal.get('birthDateStr').value;
  //   const birthDateStrConverted = birthDateStrJSON.year + '-' + birthDateStrJSON.month + '-' + birthDateStrJSON.day;
  //   this.record.birthDateStr = birthDateStrConverted;
  // }

}
