import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { UserService } from 'src/app/services/user/user.service';

import { MatSnackBar } from '@angular/material/snack-bar';

import { UserCodeWrapper } from 'src/app/domain/user-code-wrapper';

@Component({
  selector: 'app-activate-account',
  templateUrl: './activate-account.component.html',
  styleUrls: ['./activate-account.component.css']
})
export class ActivateAccountComponent implements OnInit {
  constructor(private userService: UserService, private router: Router, private snackBar: MatSnackBar) { }
  
  ngOnInit(): void {
    let route: string = this.router.url;
    let routeParts: string[] = route.split('activate-account');
    let userCodeOfNewRegisteredUser = routeParts[routeParts.length - 1].substring(1);

    const codeOfNewRegisteredUserWrapper: UserCodeWrapper = new UserCodeWrapper(userCodeOfNewRegisteredUser);

    this.userService.activateAccount(codeOfNewRegisteredUserWrapper).subscribe(
      data => {
        console.log('Account activation response: ', data);
        this.snackBar.open('Uspešno ste aktivirali korisnički nalog! Dobro došli na sajt "Medicinska oprema"!', 
            'Zatvori', { duration: 10000 });
      },
      (errorResponse: HttpErrorResponse) => {
        console.log(`Error on activating account!\n\n${errorResponse.error.textMessage}`);
        if (errorResponse.status == 400) {
          this.snackBar.open('Korisnički nalog je već aktiviran!', 'Zatvori', { duration: 5000 });
        }
      }
    );
  }
}
