import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { RegistrationComponent } from './components/registration/registration.component';
import { ActivateAccountComponent } from './components/activate-account/activate-account.component';

const routes: Routes = [
  { path: 'register', component: RegistrationComponent },
  { path: 'activate-account/:userCode', component: ActivateAccountComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
