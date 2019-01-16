import { Component, OnInit } from '@angular/core';
import {Medicine} from '../_models/medicine';
import {MedicineService} from '../_services/medicine.service';

import {ErrorMessage} from 'ng-bootstrap-form-validation';
import { ToastrService } from 'ngx-toastr';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import {FormControl, FormGroup, Validators} from '@angular/forms';
declare var $: any;
@Component({
  selector: 'app-medicine',
  templateUrl: './medicine.component.html',
  styleUrls: ['./medicine.component.css']
})
export class MedicineComponent implements OnInit {
  medicine: Medicine;
  medicinesArray: any[];

  formGroup: FormGroup;
  formGroupModal: FormGroup;
  selectedMedicine: string;
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
    private medicineService: MedicineService,

    private toastr: ToastrService
  ) { }

  ngOnInit() {
    this.getMedicines();
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
    this.formGroupModal = new FormGroup({
      description: new FormControl('', [
        Validators.required
      ]),
      name: new FormControl('', [
        Validators.required
      ]),
      sideEffects: new FormControl('', [
        Validators.required
      ]),
    });
  }

  getMedicine(): void {
    const name = this.formGroup.controls.name.value;
    // const name = +this.route.snapshot.paramMap.get('name');
    this.medicineService.getMedicine(name)
      .subscribe(resp => {
        this.medicine = resp.body;

        this.toastr.success('Pobrano informacje o leku');
      }, error => {

        this.toastr.error('Nie udało się pobrać informacji o leku');

      });

  }
  createMedicine(): void {
    this.medicineService.createMedicine(this.formGroupModal.value)
      .subscribe(resp => {
        $('#createMedicineModal').modal('toggle');
        this.toastr.success('Nowy lek został pomyślnie dodany');
        this.getMedicines();
      }, error => {
        this.toastr.error('Nie udało się dodać leku.');
      });
  }
  getMedicines(): void {
    this.medicineService.getMedicineLOV()
      .subscribe(resp => {
        this.medicinesArray = resp.body;
      }, error => {
        this.toastr.error('Nie udało się pobrać leków. Spróbuj ponownie.');
      });
  }

}
