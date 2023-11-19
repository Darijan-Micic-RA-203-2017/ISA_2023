import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  userId: any;
  role: any;

  constructor(private router: Router) { }
  
  ngOnInit(): void {
    this.userId = localStorage.getItem('userId');
    this.role = localStorage.getItem('role');
  }

  logout(): void {
    localStorage.removeItem('jwtToken');
    localStorage.removeItem('username');
    localStorage.removeItem('userId');
    localStorage.removeItem('role');
    localStorage.removeItem('exp');

    this.userId = null;
    this.role = null;

    this.router.navigateByUrl('/').then(() => { window.location.reload(); });
  }
}
