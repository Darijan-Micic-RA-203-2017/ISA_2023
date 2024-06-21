import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { OnlyAuthenticatedUsersGuard } from './guards/only-authenticated-users/only-authenticated-users.guard';

import { LandingPageComponent } from './components/landing-page/landing-page.component';
import { LoginComponent } from './components/login/login.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { ActivateAccountComponent } from './components/activate-account/activate-account.component';
import { CompanyProfileComponent } from './components/company-profile/company-profile.component';
import { MyProfileComponent } from './components/my-profile/my-profile.component';
import { MapOfOrderTransportComponent } from './components/map-of-order-transport/map-of-order-transport.component';

const routes: Routes = [
  { path: '', component: LandingPageComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegistrationComponent },
  { path: 'activate-account/:userCode', component: ActivateAccountComponent },
  { path: 'company/:id', component: CompanyProfileComponent, canActivate: [OnlyAuthenticatedUsersGuard] },
  { path: 'my-profile', component: MyProfileComponent, canActivate: [OnlyAuthenticatedUsersGuard] },
  { path: 'map-of-order-transport', component: MapOfOrderTransportComponent, canActivate: [ OnlyAuthenticatedUsersGuard ] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
