import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import jwt_decode from 'jwt-decode';

import { AuthService } from 'src/app/services/auth/auth.service';

import { MatSnackBar } from '@angular/material/snack-bar';

import { Credentials } from 'src/app/domain/credentials';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  form: any;
  isSubmitted: boolean = false;

  hidePassword: boolean = true;

  constructor(private authService: AuthService, private formBuilder: FormBuilder, private router: Router, 
      private snackBar: MatSnackBar) { }
  
  ngOnInit(): void {
    this.form = this.formBuilder.group({
      username: new FormControl('', {
        validators: [Validators.required, Validators.minLength(4), Validators.maxLength(32), 
            Validators.pattern(/^[A-Z][A-Za-z0-9\_\-\.]{2,}[A-Za-z0-9\.]$/)], 
        updateOn: 'change'
      }),
      password: new FormControl('', {
        validators: [Validators.required, Validators.minLength(4), Validators.maxLength(32)], 
        updateOn: 'change'
      })
    });
  }

  submitCredentials(): void {
    this.isSubmitted = true;

    let credentials: Credentials = new Credentials(this.form.value.username, this.form.value.password);

    this.authService.loginWith(credentials).subscribe(
      data => {
        console.log('Login response: ', data);

        localStorage.setItem('jwtToken', data.accessToken);
        let tokenInfo = this.getDecodedAccessToken(data.accessToken);
        localStorage.setItem('username', tokenInfo.sub);
        localStorage.setItem('userId', tokenInfo.userId);
        localStorage.setItem('role', tokenInfo.role);
        localStorage.setItem('exp', tokenInfo.exp);

        this.router.navigateByUrl('/').then(() => { window.location.reload(); });
      },
      (errorResponse: HttpErrorResponse) => {
        this.isSubmitted = false;

        console.log('Error on login!', errorResponse.error.textMessage);
        if (errorResponse.status == 409) {
          this.snackBar.open('Korisnikov nalog je deaktiviran!', 'Zatvori', { duration: 5000 });
        }
        if (errorResponse.status == 423) {
          this.snackBar.open('Korisnikov nalog je zaključan!', 'Zatvori', { duration: 5000 });
        }
        if (errorResponse.status == 400) {
          this.snackBar.open('Uneto je neispravno korisničko ime i/ili lozinka!', 'Zatvori', { duration: 5000 });
        }
      }
    );
  }

  getErrorMessageFor(data: string): string {
    let errorMessage: string = '';

    switch (data) {
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
        
        if (this.form.get('password').hasError('minlength') || this.form.get('password').hasError('maxlength')) {
          errorMessage = 'Lozinka mora sadržati najmanje 4, a najviše 32 znaka!';
        }
        
        break;
      default:
        errorMessage = '';
    }

    return errorMessage;
  }

  getDecodedAccessToken(token: string): any {
    try {
      return jwt_decode(token);
    } catch (error) {
      return '';
    }
  }
}
