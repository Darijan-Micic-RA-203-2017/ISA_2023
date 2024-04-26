import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { JWT_OPTIONS, JwtHelperService } from '@auth0/angular-jwt';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TokenInterceptor } from './interceptor/token-interceptor';
import { AuthService } from './services/auth/auth.service';
import { UserService } from './services/user/user.service';
import { MedicalEquipmentCompanyService } from './services/medical-equipment-company/medical-equipment-company.service';
import { MedicalEquipmentService } from './services/medical-equipment/medical-equipment.service';
import { ExchangeTermService } from './services/exchange-term/exchange-term.service';
import { EquipmentOrderService } from './services/equipment-order/equipment-order.service';
import { DetailsOfEquipmentOrderService } from './services/details-of-equipment-order/details-of-equipment-order.service';
import { OrderingService } from './services/ordering/ordering.service';

import { OnlyAuthenticatedUsersGuard } from './guards/only-authenticated-users/only-authenticated-users.guard';

import { HeaderComponent } from './components/header/header.component';
import { LoginComponent } from './components/login/login.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { ActivateAccountComponent } from './components/activate-account/activate-account.component';
import { LandingPageComponent } from './components/landing-page/landing-page.component';
import { CompanyProfileComponent } from './components/company-profile/company-profile.component';
import { MyProfileComponent } from './components/my-profile/my-profile.component';

import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule, MAT_FORM_FIELD_DEFAULT_OPTIONS } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatRadioModule } from '@angular/material/radio';
import { MatTableModule } from '@angular/material/table';
import { MatLuxonDateModule } from '@angular/material-luxon-adapter';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatChipsModule } from '@angular/material/chips';
import { MatSelectModule } from '@angular/material/select';

const MaterialComponents = [
  MatToolbarModule,
  MatButtonModule,
  MatCardModule,
  MatFormFieldModule,
  MatInputModule,
  MatIconModule,
  MatProgressSpinnerModule,
  MatSnackBarModule,
  MatRadioModule,
  MatTableModule,
  // REFERENCE: https://v12.material.angular.io/components/datepicker/overview#choosing-a-date-implementation-and-date-format-settings
  MatLuxonDateModule,
  MatDatepickerModule,
  MatChipsModule,
  MatSelectModule
];

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    LoginComponent,
    RegistrationComponent,
    ActivateAccountComponent,
    LandingPageComponent,
    CompanyProfileComponent,
    MyProfileComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    HttpClientModule,
    MaterialComponents
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true },
    { provide: JWT_OPTIONS, useValue: JWT_OPTIONS },
    JwtHelperService,
    AuthService,
    UserService,
    MedicalEquipmentCompanyService,
    MedicalEquipmentService,
    ExchangeTermService,
    EquipmentOrderService,
    DetailsOfEquipmentOrderService,
    OrderingService,
    OnlyAuthenticatedUsersGuard,
    { provide: MAT_FORM_FIELD_DEFAULT_OPTIONS, useValue: { appearance: 'fill' } }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
