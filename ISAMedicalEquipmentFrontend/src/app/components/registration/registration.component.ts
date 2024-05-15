import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';

import { UserService } from 'src/app/services/user/user.service';

import { MatSnackBar } from '@angular/material/snack-bar';

import { ProcurementManagerRegistrationReq } from 'src/app/domain/procurement-manager-registration-req';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  form: any;
  isSubmitted: boolean = false;

  hidePassword: boolean = true;
  hidePasswordConfirmation: boolean = true;

  constructor(private userService: UserService, private formBuilder: FormBuilder, private snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      emailAddress: new FormControl('', {
        validators: [Validators.required, Validators.pattern(/^[a-z0-9\_\-\.]+@[a-z]+\.[a-z\.]+$/)], 
        updateOn: 'change'
      }),
      username: new FormControl('', {
        validators: [Validators.required, Validators.minLength(4), Validators.maxLength(32), 
            Validators.pattern(/^[A-Z][A-Za-z0-9\_\-\.]{2,}[A-Za-z0-9\.]$/)], 
        updateOn: 'change'
      }),
      password: new FormControl('', {
        validators: [Validators.required, Validators.minLength(4), Validators.maxLength(32), 
            this.passwordsDoNotMatchValidator()],
        updateOn: 'blur'
      }),
      passwordConfirmation: new FormControl('', {
        validators: [Validators.required, Validators.minLength(4), Validators.maxLength(32), 
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

  submitRegistrationRequest(): void {
    this.isSubmitted = true;

    let profession: string | null = null;
    if (this.form.value.profession) {
      profession = this.form.value.profession;
    }
    let companyName: string | null = null;
    if (this.form.value.companyName) {
      companyName = this.form.value.companyName;
    }

    let procurementManagerRegistrationReq: ProcurementManagerRegistrationReq = new ProcurementManagerRegistrationReq(
        this.form.value.emailAddress, this.form.value.username, this.form.value.password, this.form.value.firstName, 
        this.form.value.lastName, this.form.value.residence, this.form.value.populatedPlace, this.form.value.country, 
        this.form.value.phoneNumber, this.form.value.personalIdentityNumber, this.form.value.gender, profession, companyName);

    this.userService.registerAsAProcurementManager(procurementManagerRegistrationReq).subscribe(
      data => {
        console.log('Registration as a procurement manager response: ', data);

        this.snackBar.open('Na adresu elektronske pošte koju ste naveli u zahtevu za registraciju ' + 
            'poslata Vam je poveznica za aktivaciju naloga.\n' + 
            'Kliknite na poslatu poveznicu kako bi završili proces registracije.', 
            'Zatvori', { duration: 60000 });

        this.isSubmitted = false;
      },
      (errorResponse: HttpErrorResponse) => {
        this.isSubmitted = false;

        console.log(`Error on registration as a procurement manager!\n\n${errorResponse.error.textMessage}`);
        if (errorResponse.status == 400) {
          if (errorResponse.error.textMessage.includes('username')) {
            this.snackBar.open('Već postoji korisnik sa istim korisničkom imenom!', 'Zatvori', { duration: 5000 });
          }
          if (errorResponse.error.textMessage.includes('Email address')) {
            this.snackBar.open('Adresa elektronske pošte povezana je sa postojećim korisničkim nalogom!', 
                'Zatvori', { duration: 5000 });
          }
        }
        if (errorResponse.status == 500) {
          this.snackBar.open('Pojavila se greška prilikom slanja elektronske poruke na serverskoj aplikaciji.\n' + 
              'Molimo Vas, pokušajte kasnije.', 'Zatvori', { duration: 10000 });
        }
      }
    );
  }

  // REFERENCE: https://blog.angular-university.io/angular-custom-validators/
  passwordsDoNotMatchValidator(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      let form = control.parent;
      let enteredPassword: string = form?.get('password')?.value;
      let enteredPasswordConfirmation: string = form?.get('passwordConfirmation')?.value;

      if (enteredPassword || enteredPasswordConfirmation) {
        if (enteredPassword !== enteredPasswordConfirmation) {
          return {
            passwordsDoNotMatch: {
              enteredPassword: enteredPassword,
              enteredPasswordConfirmation: enteredPasswordConfirmation
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
        if (this.form.get('emailAddress').hasError('required')) {
          errorMessage = 'Morate uneti adresu elektronske pošte!';
        }

        if (this.form.get('emailAddress').hasError('pattern')) {
          errorMessage = 'Adresa elektronske pošte nije ispravnog oblika!';
        }

        break;
      case 'username':
        if (this.form.get('username').hasError('required')) {
          errorMessage = 'Morate uneti korisničko ime!';
        }

        if (this.form.get('username').hasError('pattern')) {
          errorMessage = 'Korisničko ime mora početi velikim slovom i završiti se slovom ili tačkom!\n' + 
              'Dozvoljeni su znakovi \'_\', \'-\' i \'.\'.';
        }

        break;
      case 'password':
        if (this.form.get('password').hasError('required')) {
          errorMessage = 'Morate uneti lozinku!';
        }

        if (this.form.get('password').hasError('passwordsDoNotMatch')) {
          errorMessage = 'Lozinka ne poklapa se sa sadržajem polja za ponovni unos lozinke!';
        }

        break;
      case 'passwordConfirmation':
        if (this.form.get('passwordConfirmation').hasError('required')) {
          errorMessage = 'Morate ponovo uneti lozinku!';
        }

        if (this.form.get('passwordConfirmation').hasError('passwordsDoNotMatch')) {
          errorMessage = 'Sadržaj polja za ponovni unos lozinke ne poklapa se sa lozinkom!';
        }

        break;
      case 'firstName':
        if (this.form.get('firstName').hasError('required')) {
          errorMessage = 'Morate uneti ime!';
        }

        if (this.form.get('firstName').hasError('pattern')) {
          errorMessage = 'Ime nije ispravnog oblika!';
        }

        break;
      case 'lastName':
        if (this.form.get('lastName').hasError('required')) {
          errorMessage = 'Morate uneti prezime!';
        }

        if (this.form.get('lastName').hasError('pattern')) {
          errorMessage = 'Prezime nije ispravnog oblika!';
        }

        break;
      case 'residence':
        if (this.form.get('residence').hasError('required')) {
          errorMessage = 'Morate uneti ulicu i broj!';
        }

        break;
      case 'populatedPlace':
        if (this.form.get('populatedPlace').hasError('required')) {
          errorMessage = 'Morate uneti mesto!';
        }

        break;
      case 'country':
        if (this.form.get('country').hasError('required')) {
          errorMessage = 'Morate uneti državu!';
        }

        break;
      case 'gender':
        if (this.form.get('gender').hasError('required')) {
          errorMessage = 'Morate odabrati pol!';
        }

        break;
      default:
        break;
    }

    return errorMessage;
  }
}
