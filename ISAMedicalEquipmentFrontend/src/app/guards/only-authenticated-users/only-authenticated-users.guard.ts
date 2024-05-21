import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

import { AuthService } from 'src/app/services/auth/auth.service';

import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root'
})
export class OnlyAuthenticatedUsersGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router, private snackBar: MatSnackBar) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean | UrlTree> | 
      Promise<boolean | UrlTree> | boolean | UrlTree {
    if (!this.authService.hasTokenExpired()) {
      let userRole = localStorage.getItem('role');
      if (userRole) {
        return true;
      }
    }

    let deniedUrl: string = "";
    for (let i = 0; i < route.url.length; i++) {
      if (i > 0) {
        deniedUrl = deniedUrl.concat('/');
      }
      deniedUrl = deniedUrl.concat(route.url[i].path);
    }
    console.log('Guard "OnlyAuthenticatedUsersGuard" has denied activation of route "' + deniedUrl + '"!');
    this.router.navigateByUrl('/login').then(() => {
      this.snackBar.open('Nemate pravo pristupa unetoj putanji!', 'Zatvori', { duration: 7500 });
    });

    return false;
  }
}
