import { Component, OnInit } from '@angular/core';
import { History} from '../_models/history';
import { PatientHistoryService } from '../_services/patient-history.service';
import { MedicineService} from '../_services/medicine.service';
import { DiseaseService} from '../_services/disease.service';
import {ErrorMessage} from 'ng-bootstrap-form-validation';
import { ToastrService } from 'ngx-toastr';
import { ActivatedRoute } from '@angular/router';
import { NgbCalendar } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import { Patient} from '../_models/patient';

declare var $: any;
@Component({
  selector: 'app-patient-history-doctor',
  templateUrl: './patient-history-doctor.component.html',
  styleUrls: ['./patient-history-doctor.component.css']
})
export class PatientHistoryDoctorComponent implements OnInit {
  history: History[];
  medicinesArray: any[];
  diseasesArray: any[];
  patientsArray: any[];
  formGroup: FormGroup;
  formGroupModal: FormGroup;
  searchFormGroup: FormGroup;
  selectedMedicine: string;
  selectedDisease: string;
  selectedPatient: string;
  record: History;
  customErrorMessages: ErrorMessage[] = [
    {
      error: 'required',
      format: (label, error) => `Zawartość pola "${label}" nie może być pusta!`
    }];

  constructor(    private route: ActivatedRoute,
                  private historyService: PatientHistoryService,
                  private diseaseService: DiseaseService,
                  private calendar: NgbCalendar,
                  private medicineService: MedicineService,
                  private toastr: ToastrService) { }

  ngOnInit() {
    this.getDisease();
    this.getMedicine();
    this.getPatients();
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
      patientId: new FormControl('', [
        Validators.required
      ]),
      diseaseName: new FormControl('', [
        Validators.required
      ]),
      diagnosis: new FormControl('', [
        Validators.required
      ]),
      diagnosisDateStr: new FormControl('', [
        Validators.required
      ]),
      medicineName: new FormControl('', [
        Validators.required
      ]),
      medicineDiseaseDosage: new FormControl('', [
        Validators.required
      ])
    });
  }
  getPatientHistory(): void {
    const name = this.formGroup.controls.name.value;
    this.historyService.getPatientHistory(name)
      .subscribe(resp => {
        this.toastr.success('Pobrano historię chorób pacjenta');
        this.history = resp.body;
      }, error => {
        this.toastr.error('Nie udało pobrać się historii chorób pacjenta');
      });
  }
  getDisease(): void {
    this.diseaseService.getDiseaseLOV()
      .subscribe(resp => {
        this.diseasesArray = resp.body;
      }, error => {
        this.toastr.error('Nie udało się pobrać chorób. Spróbuj ponownie.');
      });
  }
  getMedicine(): void {
    this.medicineService.getMedicineLOV()
      .subscribe(resp => {
        this.medicinesArray = resp.body;
      }, error => {
        this.toastr.error('Nie udało się pobrać leków. Spróbuj ponownie.');
      });
  }
  getPatients(): void {
    this.historyService.getPatientsLOV()
      .subscribe(resp => {
        this.patientsArray = resp.body;
      }, error => {
        this.toastr.error('Nie udało się pobrać pacjentów. Spróbuj ponownie.');
      });
  }

  createHistory(): void {
    this.prepareRow();
    this.historyService.createHistory(this.formGroupModal.value)
      .subscribe(resp => {
        $('#createHistoryModal').modal('toggle');
        this.toastr.success('Poprawnie dodano wpis do historii chorób');
      }, error => {
        this.toastr.error('Nie udało się dodać wpisu do historii. Spróbuj ponownie');
      });
  }
  prepareRow(): void {
    this.record = this.formGroupModal.value;

    const diagnosisDateJSON = this.formGroupModal.get('diagnosisDateStr').value;
    const diagnosisDateConverted = diagnosisDateJSON.year + '-' + diagnosisDateJSON.month + '-' + diagnosisDateJSON.day;
    this.record.diagnosisDateStr = diagnosisDateConverted;
  }

}
