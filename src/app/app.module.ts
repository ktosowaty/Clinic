import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AngularFontAwesomeModule } from 'angular-font-awesome';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule , ReactiveFormsModule } from '@angular/forms';
import { NgBootstrapFormValidationModule } from 'ng-bootstrap-form-validation';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap'
import { ToastrModule } from 'ngx-toastr';
import { AppRoutingModule } from './app-routing.module';
import { NgSelectModule } from '@ng-select/ng-select';
import { AppComponent } from './app.component';
import { LoginPageComponent } from './login-page/login-page.component';
import {JwtInterceptor} from './_interceptors/jwt-interceptors';
import { RegisterComponent } from './register/register.component';
import { ClinicRegisterComponent } from './clinic-register/clinic-register.component';
import { OpinionComponent } from './opinion/opinion.component';
import { VisitComponent } from './visit/visit.component';
import { MedicalBenefitsComponent } from './medical-benefits/medical-benefits.component';
import { DiseaseComponent } from './disease/disease.component';
import { PatientHistoryComponent } from './patient-history/patient-history.component';
import { MedicineComponent } from './medicine/medicine.component';
import { PatientHistoryDoctorComponent } from './patient-history-doctor/patient-history-doctor.component';
import { VisitSpecializationComponent } from './visit-specialization/visit-specialization.component';
import { SecretaryRegisterComponent } from './secretary-register/secretary-register.component';
import { MainPageComponent } from './main-page/main-page.component';
import { VisitSecretaryComponent } from './visit-secretary/visit-secretary.component';
import { VisitSpecializationSecComponent } from './visit-specialization-sec/visit-specialization-sec.component';



@NgModule({
  declarations: [
    AppComponent,
    LoginPageComponent,
    RegisterComponent,
    ClinicRegisterComponent,
    OpinionComponent,
    VisitComponent,
    MedicalBenefitsComponent,
    DiseaseComponent,
    PatientHistoryComponent,
    MedicineComponent,
    PatientHistoryDoctorComponent,
    VisitSpecializationComponent,
    SecretaryRegisterComponent,
    MainPageComponent,
    VisitSecretaryComponent,
    VisitSpecializationSecComponent,

],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    NgbModule,
    NgBootstrapFormValidationModule.forRoot(),
    NgBootstrapFormValidationModule,
    BrowserAnimationsModule, // required animations module
    ToastrModule.forRoot({
        positionClass: 'toast-bottom-right'}), // ToastrModule added
    AngularFontAwesomeModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgSelectModule,
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: JwtInterceptor,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
