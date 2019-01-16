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
  templateUrl: './visit.component.html',
  styleUrls: ['./visit.component.css']
})
export class VisitComponent implements OnInit {
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
    private toastr: ToastrService,
    private router: Router,
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
      name: new FormControl('', [
        Validators.required
      ]),
    });
  }
  getDoctorAvabilities(): void {
    const name = this.formGroup.controls.name.value;
    // const name = +this.route.snapshot.paramMap.get('name');
    this.visitService.getDoctorAvabilities(name)
      .subscribe(resp => {
        this.avability = resp.body;

        this.toastr.success('Pobrano kalendarz dostępności lekarzy');
      }, error => {

        this.toastr.error('Nie udało się pobrać dostępnośći lekarzy');

      });

  }

  callReservationPatient(doctorId: number, visitStartStr: string): void {
    this.visitService.createVisitOwn(doctorId, visitStartStr)
      .subscribe(resp => {
        this.toastr.success('Umówiłeś się na wizytę!');
        this.getDoctorAvabilities();
      }, error => {
        this.toastr.success('Nie udało się umówić na wizytę');
      });
  }

}
