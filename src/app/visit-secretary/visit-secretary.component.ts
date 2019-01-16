import { Component, OnInit } from '@angular/core';
import { Patient} from '../_models/patient';
import { PatientService} from '../_services/patient.service';
import {VisitService} from '../_services/visit.service';
import { Avabilities} from '../_models/avabilities';
import { ToastrService } from 'ngx-toastr';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import { PatientHistoryService} from '../_services/patient-history.service';

@Component({
  selector: 'app-visit-secretary',
  templateUrl: './visit-secretary.component.html',
  styleUrls: ['./visit-secretary.component.css']
})
export class VisitSecretaryComponent implements OnInit {
  patientsArray: any[];
  selectedPatient: string;
  avability: Avabilities[];
  patient: Patient;
  formGroup: FormGroup;
  formGroupDoctor: FormGroup;
//  formGroupModal: FormGroup;
  constructor(
    private route: ActivatedRoute,
    private patientService: PatientService,
    private visitService: VisitService,
    private toastr: ToastrService,
    private historyService: PatientHistoryService

  ) { }

  ngOnInit() {
    this.getPatients();
    this.formGroup = new FormGroup({
      peselStr: new FormControl()
     ,
    });
    this.formGroupDoctor = new FormGroup({
      name: new FormControl()
      ,
    });
  }
  getPatient(): void {

    const peselStr = this.formGroup.controls.peselStr.value;

    this.patientService.getPatient(peselStr)
      .subscribe(resp => {
        this.patient = resp.body;
        this.toastr.success('Pobrano informację o pacjencie');
      }, error => {

        this.toastr.error('Nie udało się pobrać informacji o pacjencie');

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




  getDoctorAvabilitiesSec(): void {
    const name = this.formGroupDoctor.controls.name.value;
    // const name = +this.route.snapshot.paramMap.get('name');
    this.visitService.getDoctorAvabilities(name)
      .subscribe(resp => {
        this.avability = resp.body;

         this.toastr.success('Pobrano kalendarz dostępności lekarza');
      }, error => {

        this.toastr.error('Nie udało się pobrać kalendarza dostępności lekarza');

      });

  }
  callReservationSecretary(patientId: number, doctorId: number, visitStartStr: string): void {

    // const name = +this.route.snapshot.paramMap.get('name');
    this.visitService.createVisitBySecretary(patientId, doctorId, visitStartStr )
      .subscribe(resp => {
        this.toastr.success('Zarezerwowano wizytę dla pacjenta');
        this.getDoctorAvabilitiesSec();
      }, error => {

        this.toastr.error('Nie udało się zarezerwować wizyty dla pacjenta' +
          '');

      });

  }
}
