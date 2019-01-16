import { Component, OnInit } from '@angular/core';
import {Disease} from '../_models/disease';
import { DiseaseService} from '../_services/disease.service';
import * as $ from 'jquery';

declare var $: any
;
import {ErrorMessage} from 'ng-bootstrap-form-validation';
import { ToastrService } from 'ngx-toastr';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import {FormControl, FormGroup, Validators} from '@angular/forms';
@Component({
  selector: 'app-disease',
  templateUrl: './disease.component.html',
  styleUrls: ['./disease.component.css']
})
export class DiseaseComponent implements OnInit {
  diseasesArray: any[];
  selectedDisease: string;
  disease: Disease;
  formGroup: FormGroup;
  formGroupModal: FormGroup;

  customErrorMessages: ErrorMessage[] = [
    {
      error: 'required',
      format: (label, error) => `Zawartość pola "${label}" nie może być pusta!`
    }];
  constructor(
    private route: ActivatedRoute,
    private diseaseService: DiseaseService,

    private toastr: ToastrService
  ) { }

  ngOnInit() {
    this.getDiseases();
    $(document).ready(function() {
      const navLink = $('.nav-link')[1];
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
      description: new FormControl('', [
        Validators.required
      ]),
      name: new FormControl('', [
        Validators.required
      ]),
    });
  }

  getDisease(): void {
    const name = this.formGroup.controls.name.value;
   // const name = +this.route.snapshot.paramMap.get('name');
    this.diseaseService.getDisease(name)
      .subscribe(resp => {
        this.disease = resp.body;

        this.toastr.success('Pobrano informacje o chorobie');

  }, error => {

  this.toastr.error('Nie udało się pobrać informacji o chorobie');

});

  }
  getDiseases(): void {
    this.diseaseService.getDiseaseLOV()
      .subscribe(resp => {
        this.diseasesArray = resp.body;
      }, error => {
        this.toastr.error('Nie udało się pobrać chorób. Spróbuj ponownie.');
      });
  }
  createDisease(): void {
    this.diseaseService.createDisease(this.formGroupModal.value)
      .subscribe(resp => {
        $('#createReaderModal').modal('toggle');
        this.toastr.success('Nowa choroba została pomyślnie dodana');
        this.getDiseases();
      }, error => {
        this.toastr.error('Nie udało się dodać choroby. Spróbuj ponownie');
      });
  }

}
