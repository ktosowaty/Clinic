import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import { ClinicRegisterService} from '../_services/clinic-register.service';
import { ToastrService } from 'ngx-toastr';
import { ActivatedRoute } from '@angular/router';
import { NgbCalendar } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';
import {Patient} from '../_models/patient';
import {ErrorMessage} from 'ng-bootstrap-form-validation';
import { PatientHistoryService} from '../_services/patient-history.service';

declare var $: any;
@Component({
  selector: 'app-secretary-register',
  templateUrl: './secretary-register.component.html',
  styleUrls: ['./secretary-register.component.css']
})
export class SecretaryRegisterComponent implements OnInit {
  patientsArray: any[];
  selectedPatient: string;
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
  constructor(    private route: ActivatedRoute,
                  private clinicService: ClinicRegisterService,
                  private calendar: NgbCalendar,
                  private historyService: PatientHistoryService,
                  private toastr: ToastrService) {


  }

  ngOnInit() {
    this.getPatients();
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
  createPatientSec(): void {
   //  this.prepareForm();
    this.clinicService.postPatientBySecretary(this.formGroupModal.value)
      .subscribe(resp => {
        $('#createPatientSec').modal('toggle');
        this.toastr.success('Poprawnie zarejestrowano pacjenta');
      }, error => {
        this.toastr.error('Nie udało się zarejestrować pacjenta');
      });
  }
  // prepareForm(): void {
  //   this.record = this.formGroupModal.value;
  //
  //   const birthDateStrJSON = this.formGroupModal.get('birthDateStr').value;
  //   const birthDateStrConverted = birthDateStrJSON.year + '-' + birthDateStrJSON.month + '-' + birthDateStrJSON.day;
  //   this.record.birthDateStr = birthDateStrConverted;
  // }
  getPatients(): void {
    this.historyService.getPatientsLOV()
      .subscribe(resp => {
        this.patientsArray = resp.body;
      }, error => {
        this.toastr.error('Nie udało się pobrać pacjentów. Spróbuj ponownie.');
      });
  }
}
