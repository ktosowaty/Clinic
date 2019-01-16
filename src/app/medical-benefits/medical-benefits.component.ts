import { Component, OnInit } from '@angular/core';
import { Package} from '../_models/packages';
import { MedicalBenefitsService} from '../_services/medical-benefits.service';
import * as $ from 'jquery';

declare var $: any
;
import {ErrorMessage} from 'ng-bootstrap-form-validation';
import { ToastrService } from 'ngx-toastr';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import {FormControl, FormGroup, Validators} from '@angular/forms';


@Component({
  selector: 'app-medical-benefits',
  templateUrl: './medical-benefits.component.html',
  styleUrls: ['./medical-benefits.component.css']
})
export class MedicalBenefitsComponent implements OnInit {
  packages: Package[];
  formGroup: FormGroup;
  formGroupModal: FormGroup;
  constructor(
    private packageService: MedicalBenefitsService,
    private toastr: ToastrService
  ) { }


  ngOnInit() {
    this.getPackages();
    $(document).ready(function() {
      var navLink = $('.nav-link')[1];
      $('.nav-link').each(function(this) {
        $(this).removeClass('active');
      });
      $(navLink).addClass('active');
    });
  }
  getPackages(): void {

    this.packageService.getPackages()
      .subscribe(resp => {
        this.packages = resp.body;
        console.log(this.packages);
        this.toastr.success('Pobrano informacje o pakietach medycznych');
      }, error => {

        this.toastr.error('Nie udało się pobrać informacji o pakietach medycznych');

      });
  }
  callPruchase(id: number): void {
    this.packageService.purchasePackage(id)
      .subscribe(resp => {
        this.toastr.success('Zakupiono pakiet');
      }, error => {
        this.toastr.success('Nie udało się zakupić pakietu. Spróbuj ponownie');
      });
  }
}
