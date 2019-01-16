import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Opinion} from '../_models/opinion';
import { OpinionService} from '../_services/opinion.service';
import {ErrorMessage} from 'ng-bootstrap-form-validation';
import { ToastrService } from 'ngx-toastr';
import { ActivatedRoute } from '@angular/router';
import { DoctorService} from '../_services/doctor.service';
import { Router } from '@angular/router';
import {FormControl, FormGroup, Validators} from '@angular/forms';
declare var $: any;
@Component({
  selector: 'app-opinion',
  templateUrl: './opinion.component.html',
  styleUrls: ['./opinion.component.css']
})
export class OpinionComponent implements OnInit {
  opinionMock = [
    {id: 'REALLY_BAD', name: 'Bardzo zły'},
    {id: 'BAD', name: 'Zły'},
    {id: 'DECENT', name: 'Przyzwoity'},
    {id: 'GOOD', name: 'Dobry'},
    {id: 'REALLY_GOOD', name: 'Bardzo dobry'},
  ];
  selectedOpinionId: string;
  opinion: Opinion;
  formGroup: FormGroup;
  formGroupModal: FormGroup;
  doctorsArray: any[];
  selectedDoctorId: string;
  customErrorMessages: ErrorMessage[] = [
    {
      error: 'required',
      format: (label, error) => `Zawartość pola "${label}" nie może być pusta!`
    },
    {
      error: 'required',
      format: (label, error) => `Zawartość pola "${label}" nie może być pusta!`
    },
    {
      error: 'required',
      format: (label, error) => `Zawartość pola "${label}" nie może być pusta!`
    }];
  constructor(
    private route: ActivatedRoute,
    private opinionService: OpinionService,
    private doctorService: DoctorService,
    private toastr: ToastrService
  ) { }
  ngOnInit() {
    this.getDoctors();
    $(document).ready(function() {
      let navLink = $('.nav-link')[1];
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
    this.formGroupModal = new FormGroup({
      doctorId: new FormControl('', [
        Validators.required
      ]),
      opinion: new FormControl('', [
        Validators.required
      ]),
      rate: new FormControl('', [
        Validators.required
      ]),
    });
  }
  getOpinion(): void {
    const name = this.formGroup.controls.name.value;
    // const name = +this.route.snapshot.paramMap.get('name');
    this.opinionService.getOpinion(name)
      .subscribe(resp => {
        this.opinion = resp.body;

        this.toastr.success('Pobrano opinie o lekarzu');
      }, error => {

        this.toastr.error('Nie udało się pobrać opinii o lekarzu');

      });

  }
  createOpinion(): void {
    this.opinionService.createOpinion(this.formGroupModal.value)
      .subscribe(resp => {
        $('#createOpinionModal').modal('toggle');
        this.toastr.success('Opinia została prawidłowo dodana!');
      }, error => {
        this.toastr.error('Nie udało się dodać opinii. Nie byłeś na wizycie u tego doktora');
      });
  }
  getDoctors(): void {
    this.doctorService.getDoctorsLOV()
      .subscribe(resp => {
        this.doctorsArray = resp.body;
      }, error => {
        this.toastr.error('Nie udało się pobrać lekarzy. Spróbuj ponownie');
      });
  }
}
