import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  username: any;
  userId: any;
  role: any;
  roleNameForShowPurposes: any;

  constructor(private router: Router) { }
  
  ngOnInit(): void {
    this.username = localStorage.getItem('username');
    this.userId = localStorage.getItem('userId');
    this.role = localStorage.getItem('role');
    this.roleNameForShowPurposes = this.getRoleName();
  }

  logout(): void {
    localStorage.removeItem('jwtToken');
    localStorage.removeItem('username');
    localStorage.removeItem('userId');
    localStorage.removeItem('role');
    localStorage.removeItem('exp');

    this.username = null;
    this.userId = null;
    this.role = null;
    this.roleNameForShowPurposes = null;

    this.router.navigateByUrl('/').then(() => { window.location.reload(); });
  }

  getRoleName(): string {
    if (!this.role) {
      return '';
    }

    let roleName: string = '';
    switch (this.role) {
      case 'ROLE_PROCUREMENT_MANAGER':
        roleName = 'Menadžer nabavke';

        break;
      case 'ROLE_COMPANY_ADMINISTRATOR':
        roleName = 'Administrator kompanije';
        
        break;
      case 'ROLE_SYSTEM_ADMINISTRATOR':
        roleName = 'Administrator sistema';

        break;
      default:
        roleName = '';
    }

    return roleName;
  }
}
