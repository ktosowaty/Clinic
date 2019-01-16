import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { LoginPageComponent } from './login-page/login-page.component';
import { AuthGuard } from './_guards/auth.guard';
import {RegisterComponent} from './register/register.component';
import {ClinicRegisterComponent} from './clinic-register/clinic-register.component';
import {OpinionComponent} from './opinion/opinion.component';
import {VisitComponent} from './visit/visit.component';
import {MedicalBenefitsComponent} from './medical-benefits/medical-benefits.component';
import {DiseaseComponent} from './disease/disease.component';
import { PatientHistoryComponent} from './patient-history/patient-history.component';
import { PatientHistoryDoctorComponent} from './patient-history-doctor/patient-history-doctor.component';
import {MedicineComponent} from './medicine/medicine.component';
import {VisitSpecializationComponent} from './visit-specialization/visit-specialization.component';
import { SecretaryRegisterComponent} from './secretary-register/secretary-register.component';
import { MainPageComponent} from './main-page/main-page.component';
import { VisitSpecializationSecComponent} from './visit-specialization-sec/visit-specialization-sec.component';
import { VisitSecretaryComponent} from './visit-secretary/visit-secretary.component';

const routes: Routes = [

  { path: 'login', component: LoginPageComponent},
  { path: 'register', component: RegisterComponent},
  { path: 'clinic-register', component: ClinicRegisterComponent},
  { path: 'opinion', component: OpinionComponent, canActivate: [AuthGuard]},
  { path: 'visit', component: VisitComponent},
  { path: 'medical-benefits', component: MedicalBenefitsComponent},
  { path: 'disease', component: DiseaseComponent},
  { path: 'own-history', component: PatientHistoryComponent},
  { path: 'doctor-history', component: PatientHistoryDoctorComponent},
  { path: 'medicine', component: MedicineComponent},
  { path: 'visit', component: VisitComponent},
  { path: 'visit-specialization', component: VisitSpecializationComponent},
  { path: 'secretary-register', component: SecretaryRegisterComponent},
  { path: 'visit-specialization', component: VisitSpecializationComponent},
  { path: 'secretary-register', component: SecretaryRegisterComponent},
  { path: 'visit-secretary', component: VisitSecretaryComponent},
  { path: 'visit-secretary-spec', component: VisitSpecializationSecComponent},

  { path: 'main-page', component: MainPageComponent}, ];



@NgModule({
  imports: [RouterModule.forRoot(routes, {onSameUrlNavigation: 'reload'})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
