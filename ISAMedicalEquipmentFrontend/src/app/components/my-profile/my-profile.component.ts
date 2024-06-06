import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';

import { AuthService } from 'src/app/services/auth/auth.service';
import { UserService } from 'src/app/services/user/user.service';
import { OrderingService } from 'src/app/services/ordering/ordering.service';

import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';

import { DateTime, Duration } from 'luxon';
import { User } from 'src/app/domain/user/user';
import { ProcurementManager } from 'src/app/domain/user/procurement-manager';
import { CompanyAdministrator } from 'src/app/domain/user/company-administrator';
import { SystemAdministrator } from 'src/app/domain/user/system-administrator';
import { LoyaltyProgram } from 'src/app/domain/user/loyalty-program';
import { ExchangeTerm } from 'src/app/domain/term/exchange-term';
import { Complaint } from 'src/app/domain/complaint/complaint';

@Component({
  selector: 'app-my-profile',
  templateUrl: './my-profile.component.html',
  styleUrls: ['./my-profile.component.css']
})
export class MyProfileComponent implements OnInit {
  formForUser: any;
  isFormForUserSubmitted: boolean = false;
  hideNewPassword: boolean = true;
  hideNewPasswordConfirmation: boolean = true;
  procurementManager: ProcurementManager | undefined = undefined;
  companyAdministrator: CompanyAdministrator | undefined = undefined;
  systemAdministrator: SystemAdministrator | undefined = undefined;

  displayedColumnsOfLoyaltyProgram: string[] = ['name', 'necessaryPoints', 'pointsGainedForEachSuccessfulExchange'];
  loyaltyProgram: LoyaltyProgram[] = [];
  loyaltyProgramDataSource: MatTableDataSource<LoyaltyProgram> = new MatTableDataSource<LoyaltyProgram>(this.loyaltyProgram);

  isTermForEquipmentOrderBeingCancelled: boolean = false;
  displayedColumnsOfTerms: string[] = ['startingTime', 'endingTime', 'termCancellation'];
  terms: ExchangeTerm[] = [];
  termsDataSource: MatTableDataSource<ExchangeTerm> = new MatTableDataSource<ExchangeTerm>(this.terms);

  displayedColumnsOfComplaints: string[] = ['submittedAt', 'subject', 'status', 'answer', 'answeredAt'];
  complaints: Complaint[] = [];
  complaintsDataSource: MatTableDataSource<Complaint> = new MatTableDataSource<Complaint>(this.complaints);

  userId: number = 0;

  constructor(private authService: AuthService, private userService: UserService, private orderingService: OrderingService, 
      private formBuilder: FormBuilder, private snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.initializeFormForUser();

    this.userId = this.authService.getUserId();
    this.userService.findById(this.userId).subscribe(
      data => {
        console.log('Retrieving user by id response: ', data);
        if (data.lastPasswordResetDate) {
          data.lastPasswordResetDate = DateTime.fromMillis(data.lastPasswordResetDate);
        }
        if (data.employedSince) {
          data.employedSince = DateTime.fromMillis(data.employedSince);
        }
        if (data.exchangeTerms) {
          for (let t of data.exchangeTerms) {
            t.startingTime = DateTime.fromMillis(t.startingTime);
            t.endingTime = DateTime.fromMillis(t.endingTime);
          }
        }
        if (data.complaints) {
          for (let c of data.complaints) {
            c.submittedAt = DateTime.fromMillis(c.submittedAt);
            if (c.answeredAt) {
              c.answeredAt = DateTime.fromMillis(c.answeredAt);
            }
          }
        }

        // REFERENCE: https://stackoverflow.com/questions/45964008/typescript-instanceof-not-working?noredirect=1&lq=1
        if (data.roles[0].name == 'ROLE_PROCUREMENT_MANAGER') {
          this.procurementManager = data;
        } else if (data.roles[0].name == 'ROLE_COMPANY_ADMINISTRATOR') {
          this.companyAdministrator = data;
        } else if (data.roles[0].name == 'ROLE_SYSTEM_ADMINISTRATOR') {
          this.systemAdministrator = data;
        }

        this.fillFormForUserWithData();
      },
      (errorResponse: HttpErrorResponse) => {
        console.log(`Error on retrieving user by id!\n\n${errorResponse.error.textMessage}`);
        this.snackBar.open('Korisnik nije mogao biti dobavljen!', 'Zatvori', { duration: 5000 });
      }
    );
  }

  initializeFormForUser(): void {
    this.formForUser = this.formBuilder.group({
      role: new FormControl({ value: 'ROLE_PROCUREMENT_MANAGER', disabled: true }, {
        validators: [Validators.required, 
            Validators.pattern(/^ROLE_PROCUREMENT_MANAGER$|^ROLE_COMPANY_ADMINISTRATOR$|^ROLE_SYSTEM_ADMINISTRATOR$/)], 
        updateOn: 'change'
      }),
      emailAddress: new FormControl({ value: '', disabled: true }, {
        validators: [Validators.required, Validators.pattern(/^[a-z0-9\_\-\.]+@[a-z]+\.[a-z\.]+$/)], 
        updateOn: 'change'
      }),
      username: new FormControl('', {
        validators: [Validators.required, Validators.minLength(4), Validators.maxLength(32), 
            Validators.pattern(/^[A-Z][A-Za-z0-9\_\-\.]{2,}[A-Za-z0-9\.]$/)], 
        updateOn: 'change'
      }),
      newPassword: new FormControl('', {
        // REFERENCE: https://stackoverflow.com/questions/16334765/regular-expression-for-not-allowing-spaces-in-the-input-field
        validators: [Validators.minLength(4), Validators.maxLength(32), Validators.pattern(/^\S*$/), 
            this.passwordsDoNotMatchValidator()], 
        updateOn: 'blur'
      }),
      newPasswordConfirmation: new FormControl('', {
        // REFERENCE: https://stackoverflow.com/questions/16334765/regular-expression-for-not-allowing-spaces-in-the-input-field
        validators: [Validators.minLength(4), Validators.maxLength(32), Validators.pattern(/^\S*$/), 
            this.passwordsDoNotMatchValidator()], 
        updateOn: 'blur'
      }),
      firstName: new FormControl('', {
        validators: [Validators.required, Validators.pattern(/^[A-Z\p{L}][a-z\p{L}]+([ -][A-Z\p{L}][a-z\p{L}]+)*$/u)], 
        updateOn: 'change'
      }),
      lastName: new FormControl('', {
        validators: [Validators.required, Validators.pattern(/^[A-Z\p{L}][a-z\p{L}]+([ -][A-Z\p{L}][a-z\p{L}]+)*$/u)], 
        updateOn: 'change'
      }),
      residence: new FormControl('', { validators: [Validators.required], updateOn: 'change' }),
      populatedPlace: new FormControl('', { validators: [Validators.required], updateOn: 'change' }),
      country: new FormControl('', { validators: [Validators.required], updateOn: 'change' }),
      phoneNumber: new FormControl('', {
        validators: [Validators.required, Validators.pattern(/^\+[0-9]{1,3} [0-9]{4,13}$/)], 
        updateOn: 'change'
      }),
      personalIdentityNumber: new FormControl('', {
        validators: [Validators.required, Validators.pattern(/^[0-9]{13}$/)], 
        updateOn: 'change'
      }),
      // REFERENCE: https://stackoverflow.com/questions/70435739/typescript-angular-mat-radio-button-change-option-programmatically
      gender: new FormControl('', { validators: [Validators.required], updateOn: 'change' }),
      profession: new FormControl(''),
      companyName: new FormControl('')
    });
  }

  fillFormForUserWithData(): void {
    // REFERENCE: https://stackoverflow.com/a/55275042
    if (this.procurementManager) {
      this.formForUser.setValue({
        role: this.procurementManager.roles[0].name,
        emailAddress: this.procurementManager.emailAddress,
        username: this.procurementManager.username,
        newPassword: '',
        newPasswordConfirmation: '',
        firstName: this.procurementManager.firstName,
        lastName: this.procurementManager.lastName,
        residence: this.procurementManager.residence,
        populatedPlace: this.procurementManager.populatedPlace,
        country: this.procurementManager.country,
        phoneNumber: this.procurementManager.phoneNumber,
        personalIdentityNumber: this.procurementManager.personalIdentityNumber,
        gender: this.procurementManager.gender,
        profession: this.procurementManager.profession,
        companyName: this.procurementManager.companyName
      });

      this.loyaltyProgram.pop();
      this.loyaltyProgram.push(this.procurementManager.loyaltyProgram);
      this.loyaltyProgramDataSource.data = this.loyaltyProgram;
      this.terms = this.procurementManager.exchangeTerms;
      this.termsDataSource.data = this.terms;
      this.complaints = this.procurementManager.complaints;
      this.complaintsDataSource.data = this.complaints;
    } else if (this.companyAdministrator) {
      this.formForUser.setValue({
        role: this.companyAdministrator.roles[0].name,
        emailAddress: this.companyAdministrator.emailAddress,
        username: this.companyAdministrator.username,
        newPassword: '',
        newPasswordConfirmation: '',
        firstName: this.companyAdministrator.firstName,
        lastName: this.companyAdministrator.lastName,
        residence: this.companyAdministrator.residence,
        populatedPlace: this.companyAdministrator.populatedPlace,
        country: this.companyAdministrator.country,
        phoneNumber: this.companyAdministrator.phoneNumber,
        personalIdentityNumber: this.companyAdministrator.personalIdentityNumber,
        gender: this.companyAdministrator.gender,
        profession: this.companyAdministrator.profession,
        companyName: this.companyAdministrator.companyName
      });

      this.loyaltyProgram.pop();
      this.loyaltyProgram.push(this.companyAdministrator.loyaltyProgram);
      this.loyaltyProgramDataSource.data = this.loyaltyProgram;
      this.terms = this.companyAdministrator.exchangeTerms;
      this.termsDataSource.data = this.terms;
      this.complaints = this.companyAdministrator.complaints;
      this.complaintsDataSource.data = this.complaints;
    } else if (this.systemAdministrator) {
      this.formForUser.setValue({
        role: this.systemAdministrator.roles[0].name,
        emailAddress: this.systemAdministrator.emailAddress,
        username: this.systemAdministrator.username,
        newPassword: '',
        newPasswordConfirmation: '',
        firstName: this.systemAdministrator.firstName,
        lastName: this.systemAdministrator.lastName,
        residence: this.systemAdministrator.residence,
        populatedPlace: this.systemAdministrator.populatedPlace,
        country: this.systemAdministrator.country,
        phoneNumber: this.systemAdministrator.phoneNumber,
        personalIdentityNumber: this.systemAdministrator.personalIdentityNumber,
        gender: this.systemAdministrator.gender,
        profession: this.systemAdministrator.profession,
        companyName: this.systemAdministrator.companyName
      });
    }
  }

  editProfile(): void {
    this.isFormForUserSubmitted = true;

    let newPassword: string = this.formForUser.value.newPassword;
    let profession: string | null = null;
    if (this.formForUser.value.profession) {
      profession = this.formForUser.value.profession;
    }
    let companyName: string | null = null;
    if (this.formForUser.value.companyName) {
      companyName = this.formForUser.value.companyName;
    }

    let userToBeEdited: User | undefined = undefined;
    if (this.procurementManager) {
      this.procurementManager.username = this.formForUser.value.username;
      if (newPassword) {
        this.procurementManager.password = newPassword;
        this.procurementManager.lastPasswordResetDate = DateTime.now();
      }
      this.procurementManager.firstName = this.formForUser.value.firstName;
      this.procurementManager.lastName = this.formForUser.value.lastName;
      this.procurementManager.residence = this.formForUser.value.residence;
      this.procurementManager.populatedPlace = this.formForUser.value.populatedPlace;
      this.procurementManager.country = this.formForUser.value.country;
      this.procurementManager.phoneNumber = this.formForUser.value.phoneNumber;
      this.procurementManager.personalIdentityNumber = this.formForUser.value.personalIdentityNumber;
      this.procurementManager.gender = this.formForUser.value.gender;
      this.procurementManager.profession = profession;
      this.procurementManager.companyName = companyName;

      userToBeEdited = this.procurementManager;
    } else if (this.companyAdministrator) {
      this.companyAdministrator.username = this.formForUser.value.username;
      if (newPassword) {
        this.companyAdministrator.password = newPassword;
        this.companyAdministrator.lastPasswordResetDate = DateTime.now();
      }
      this.companyAdministrator.firstName = this.formForUser.value.firstName;
      this.companyAdministrator.lastName = this.formForUser.value.lastName;
      this.companyAdministrator.residence = this.formForUser.value.residence;
      this.companyAdministrator.populatedPlace = this.formForUser.value.populatedPlace;
      this.companyAdministrator.country = this.formForUser.value.country;
      this.companyAdministrator.phoneNumber = this.formForUser.value.phoneNumber;
      this.companyAdministrator.personalIdentityNumber = this.formForUser.value.personalIdentityNumber;
      this.companyAdministrator.gender = this.formForUser.value.gender;
      this.companyAdministrator.profession = profession;
      this.companyAdministrator.companyName = companyName;

      userToBeEdited = this.companyAdministrator;
    } else if (this.systemAdministrator) {
      this.systemAdministrator.username = this.formForUser.value.username;
      if (newPassword) {
        this.systemAdministrator.password = newPassword;
        this.systemAdministrator.lastPasswordResetDate = DateTime.now();
      }
      this.systemAdministrator.firstName = this.formForUser.value.firstName;
      this.systemAdministrator.lastName = this.formForUser.value.lastName;
      this.systemAdministrator.residence = this.formForUser.value.residence;
      this.systemAdministrator.populatedPlace = this.formForUser.value.populatedPlace;
      this.systemAdministrator.country = this.formForUser.value.country;
      this.systemAdministrator.phoneNumber = this.formForUser.value.phoneNumber;
      this.systemAdministrator.personalIdentityNumber = this.formForUser.value.personalIdentityNumber;
      this.systemAdministrator.gender = this.formForUser.value.gender;
      this.systemAdministrator.profession = profession;
      this.systemAdministrator.companyName = companyName;

      userToBeEdited = this.systemAdministrator;
    } else {
      return;
    }

    this.userService.edit(userToBeEdited).subscribe(
      data => {
        console.log(`Editing user with id = ${userToBeEdited?.id} response: `, data);
        this.snackBar.open('Vaši lični podaci uspešno su izmenjeni. Ako ste promenili korisničko ime i/ili lozinku, ' 
            + 'neophodno je da se odjavite i ponovo prijavite sa novim kredencijalima kako bi mogli da nastavite da ' 
            + 'koristite aplikaciju.', 
            'Zatvori', { duration: 5000 });

        this.isFormForUserSubmitted = false;
      },
      (errorResponse: HttpErrorResponse) => {
        this.isFormForUserSubmitted = false;

        console.log(`Error on editing user with id = ${userToBeEdited?.id}!\n\n${errorResponse.error.textMessage}`);
        this.snackBar.open('Došlo je do greške prilikom izmene Vaših ličnih podataka!', 'Zatvori', { duration: 5000 });
      }
    );
  }

  didTermAlreadyStart(term: ExchangeTerm): boolean {
    let currentTime: DateTime = DateTime.now();
    // REFERENCE: https://moment.github.io/luxon/#/tour?id=durations
    let differenceBetweenCurrentTimeAndStartingTimeOfTerm: Duration = currentTime.diff(term.startingTime, ['milliseconds']);
    if (differenceBetweenCurrentTimeAndStartingTimeOfTerm.milliseconds >= 0) {
      return true;
    }

    return false;
  }

  cancelTermForEquipmentOrder(exchangeTermId: number): void {
    this.isTermForEquipmentOrderBeingCancelled = true;

    this.orderingService.cancelOrder(exchangeTermId, this.userId).subscribe(
      data => {
        console.log('Cancelling order response: ', data);

        let indexOfCancelledTerm: number = this.terms.findIndex((value: ExchangeTerm) => { value.id == exchangeTermId });
        this.terms.splice(indexOfCancelledTerm, 1);
        this.termsDataSource.data = this.terms;

        let numberOfPenaltyPoints: number = Number.parseInt(data.textMessage.split('penalized with ')[1].substring(0));
        if (this.procurementManager) {
          this.procurementManager.penaltyPoints += numberOfPenaltyPoints;
        }
        this.snackBar.open(`Termin za preuzimanje opreme uspešno je otkazan. Kažnjeni ste sa ` 
            + `${numberOfPenaltyPoints} kaznenih poena!`, 
            'Zatvori', { duration: 10000 });

        this.isTermForEquipmentOrderBeingCancelled = false;
      },
      (errorResponse: HttpErrorResponse) => {
        this.isTermForEquipmentOrderBeingCancelled = false;

        console.log(`Error on cancelling order!\n\n${errorResponse.error.textMessage}`);
      }
    );
  }

  // REFERENCE: https://blog.angular-university.io/angular-custom-validators/
  passwordsDoNotMatchValidator(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      let form = control.parent;
      let enteredNewPassword: string = form?.get('newPassword')?.value;
      let enteredNewPasswordConfirmation: string = form?.get('newPasswordConfirmation')?.value;

      if (enteredNewPassword || enteredNewPasswordConfirmation) {
        if (enteredNewPassword !== enteredNewPasswordConfirmation) {
          return {
            passwordsDoNotMatch: {
              enteredPassword: enteredNewPassword,
              enteredPasswordConfirmation: enteredNewPasswordConfirmation
            }
          };
        }
      }

      return null;
    };
  }

  getErrorMessageFor(data: string): string {
    let errorMessage: string = '';

    switch (data) {
      case 'emailAddress':
        if (this.formForUser.get('emailAddress').hasError('required')) {
          errorMessage = 'Morate uneti adresu elektronske pošte!';
        }

        if (this.formForUser.get('emailAddress').hasError('pattern')) {
          errorMessage = 'Adresa elektronske pošte nije ispravnog oblika!';
        }

        break;
      case 'username':
        if (this.formForUser.get('username').hasError('required')) {
          errorMessage = 'Morate uneti korisničko ime!';
        }

        if (this.formForUser.get('username').hasError('pattern')) {
          errorMessage = 'Korisničko ime mora početi velikim slovom i završiti se slovom ili tačkom!\n' + 
              'Dozvoljeni su znakovi \'_\', \'-\' i \'.\'.';
        }

        break;
      case 'newPassword':
        if (this.formForUser.get('newPassword').hasError('pattern')) {
          errorMessage = 'Lozinka ne sme sadržati razmake!';
        }

        if (this.formForUser.get('newPassword').hasError('passwordsDoNotMatch')) {
          errorMessage = 'Lozinka ne poklapa se sa sadržajem polja za ponovni unos lozinke!';
        }

        break;
      case 'newPasswordConfirmation':
        if (this.formForUser.get('newPasswordConfirmation').hasError('pattern')) {
          errorMessage = 'Lozinka ne sme sadržati razmake!';
        }

        if (this.formForUser.get('newPasswordConfirmation').hasError('passwordsDoNotMatch')) {
          errorMessage = 'Sadržaj polja za ponovni unos lozinke ne poklapa se sa lozinkom!';
        }

        break;
      case 'firstName':
        if (this.formForUser.get('firstName').hasError('required')) {
          errorMessage = 'Morate uneti ime!';
        }

        if (this.formForUser.get('firstName').hasError('pattern')) {
          errorMessage = 'Ime nije ispravnog oblika!';
        }

        break;
      case 'lastName':
        if (this.formForUser.get('lastName').hasError('required')) {
          errorMessage = 'Morate uneti prezime!';
        }

        if (this.formForUser.get('lastName').hasError('pattern')) {
          errorMessage = 'Prezime nije ispravnog oblika!';
        }

        break;
      case 'residence':
        if (this.formForUser.get('residence').hasError('required')) {
          errorMessage = 'Morate uneti ulicu i broj!';
        }

        break;
      case 'populatedPlace':
        if (this.formForUser.get('populatedPlace').hasError('required')) {
          errorMessage = 'Morate uneti mesto!';
        }

        break;
      case 'country':
        if (this.formForUser.get('country').hasError('required')) {
          errorMessage = 'Morate uneti državu!';
        }

        break;
      case 'gender':
        if (this.formForUser.get('gender').hasError('required')) {
          errorMessage = 'Morate odabrati pol!';
        }

        break;
      default:
        break;
    }

    return errorMessage;
  }

  showDateTime(dateTime: DateTime | null): string {
    if (!dateTime) {
      return '';
    }

    let weekday: string = '';
    switch (dateTime.weekday) {
      case 1:
        weekday = 'Ponedeljak';

        break;
      case 2:
        weekday = 'Utorak';

        break;
      case 3:
        weekday = 'Sreda';

        break;
      case 4:
        weekday = 'Četvrtak';

        break;
      case 5:
        weekday = 'Petak';

        break;
      case 6:
        weekday = 'Subota';

        break;
      case 7:
        weekday = 'Nedelja';

        break;
      default:
        break;
    }
    let hour: string = dateTime.hour.toString();
    if (hour.length == 1) {
      hour = '0'.concat(hour);
    }
    let minute: string = dateTime.minute.toString();
    if (minute.length == 1) {
      minute = '0'.concat(minute);
    }

    let dateTimeAsString: string = weekday.concat(', ', dateTime.day.toString(), '.', dateTime.month.toString(), '.', 
        dateTime.year.toString(), '. u ', hour, ':', minute);

    return dateTimeAsString;
  }

  showSubjectOfComplaint(subject: string): string {
    let subjectOfComplaint: string = '';
    switch (subject) {
      case 'COMPANY':
        subjectOfComplaint = 'Kompanija';

        break;
      case 'COMPANY_ADMINISTRATOR':
        subjectOfComplaint = 'Administrator kompanije';

        break;
      default:
        break;
    }

    return subjectOfComplaint;
  }

  showStatusOfComplaint(status: string): string {
    let statusOfComplaint: string = '';
    switch (status) {
      case 'UNRESOLVED':
        statusOfComplaint = 'Nerešena';

        break;
      case 'ACCEPTED':
        statusOfComplaint = 'Prihvaćena';

        break;
      case 'DENIED':
        statusOfComplaint = 'Odbijena';

        break;
      default:
        break;
    }

    return statusOfComplaint;
  }
}
