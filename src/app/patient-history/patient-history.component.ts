import { Component, OnInit } from '@angular/core';
import { History} from '../_models/history';
import {PatientHistoryService} from '../_services/patient-history.service';
declare var $: any;
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ErrorMessage} from 'ng-bootstrap-form-validation';
import { ToastrService } from 'ngx-toastr';
@Component({
  selector: 'app-patient-history',
  templateUrl: './patient-history.component.html',
  styleUrls: ['./patient-history.component.css']
})
export class PatientHistoryComponent implements OnInit {
  history: History[];
  formGroup: FormGroup;
  constructor(private historyService: PatientHistoryService,
              private toastr: ToastrService) { }

  ngOnInit() {
    this.getOwnHistory();
    $(document).ready(function() {
      var navLink = $('.nav-link')[1];
      $('.nav-link').each(function(this) {
        $(this).removeClass('active');
      });
      $(navLink).addClass('active');
    });
  }
  getOwnHistory(): void {
    this.historyService.getOwnHistory()
      .subscribe(resp => {
        this.history = resp.body;
      }, error => {
        this.toastr.error('Nie udało się załadować historii pacjenta.');

      });
  }
}
