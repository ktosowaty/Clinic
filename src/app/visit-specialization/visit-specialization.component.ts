import { Component, OnInit } from '@angular/core';
import { Avabilities} from '../_models/avabilities';
import { Visit} from '../_models/visit';
import {VisitService} from '../_services/visit.service';
import {ErrorMessage} from 'ng-bootstrap-form-validation';
import { ToastrService } from 'ngx-toastr';
import { ActivatedRoute } from '@angular/router';

import { Router } from '@angular/router';
import {FormControl, FormGroup, Validators} from '@angular/forms';

declare var $: any;
@Component({
  selector: 'app-visit',
  templateUrl: './visit-specialization.component.html',
  styleUrls: ['./visit-specialization.component.css']
})
export class VisitSpecializationComponent implements OnInit {
  specialization = [
    {id: 'SURGEON', name: 'Chirurg'},
    {id: 'CARDIOLOGIST', name: 'Kardiolog'},
  ];
  selectedSpecializationeId: string;
  avability: Avabilities[];
  formGroup: FormGroup;
  formGroupModal: FormGroup;
  searchFormGroup: FormGroup;

  customErrorMessages: ErrorMessage[] = [
    {
      error: 'required',
      format: (label, error) => `Zawartość pola "${label}" nie może być pusta!`
    }];

  constructor(
    private route: ActivatedRoute,
    private visitService: VisitService,
    private toastr: ToastrService
  ) { }

  ngOnInit() {
    $(document).ready(function() {
      var navLink = $('.nav-link')[1];
      $('.nav-link').each(function(this) {
        $(this).removeClass('active');
      });
      $(navLink).addClass('active');
    });

    this.formGroup = new FormGroup({
      specialization: new FormControl('', [
        Validators.required
      ]),
    });
  }
  getSpecializationAvabilities(): void {
    const specialization = this.formGroup.controls.specialization.value;
    // const name = +this.route.snapshot.paramMap.get('name');
    this.visitService.getSpecializationAvabilities(specialization)
      .subscribe(resp => {
        this.avability = resp.body;

        this.toastr.success('Pobrano kalendarz specjalistów');
      }, error => {

        this.toastr.error('Nie udało się pobrać kalendarza dostępności');

      });

  }
  callReservationPatient(doctorId: number, visitStartStr: string): void {
    this.visitService.createVisitOwn(doctorId, visitStartStr)
      .subscribe(resp => {
        this.toastr.success('Egzemplarz książki został zwrócony pomyślnie!');
        this.getSpecializationAvabilities();
      }, error => {
        this.toastr.success('Nie udało się zwrócić egzemplarza książki. Spróbuj ponownie.');
      });
  }

}
