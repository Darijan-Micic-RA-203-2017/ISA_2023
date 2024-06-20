import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

import tt from '@tomtom-international/web-sdk-maps';

import { AuthService } from 'src/app/services/auth/auth.service';
import { UserService } from 'src/app/services/user/user.service';

import { MatSnackBar } from '@angular/material/snack-bar';

import { DateTime } from 'luxon';
import { ProcurementManager } from 'src/app/domain/user/procurement-manager';
import { CompanyAdministrator } from 'src/app/domain/user/company-administrator';
import { SystemAdministrator } from 'src/app/domain/user/system-administrator';

@Component({
  selector: 'app-map-of-order-transport',
  templateUrl: './map-of-order-transport.component.html',
  styleUrls: ['./map-of-order-transport.component.css']
})
export class MapOfOrderTransportComponent implements OnInit {
  map: any;
  marker: any;

  procurementManager: ProcurementManager | undefined = undefined;
  companyAdministrator: CompanyAdministrator | undefined = undefined;
  systemAdministrator: SystemAdministrator | undefined = undefined;

  userId: number = 0;

  constructor(private authService: AuthService, private userService: UserService, private snackBar: MatSnackBar) { }

  ngOnInit(): void {
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

        this.initializeMap();
      },
      (errorResponse: HttpErrorResponse) => {
        console.log(`Error on retrieving user by id!\n\n${errorResponse.error.textMessage}`);
        this.snackBar.open('Korisnik nije mogao biti dobavljen!', 'Zatvori', { duration: 5000 });
      }
    );
  }

  initializeMap(): void {
    let companyLatitude: number = 0.0;
    let companyLongitude: number = 0.0;
    if (this.procurementManager) {
      companyLatitude = this.procurementManager.companyLatitude;
      companyLongitude = this.procurementManager.companyLongitude;
    } else if (this.companyAdministrator) {
      companyLatitude = this.companyAdministrator.companyLatitude;
      companyLongitude = this.companyAdministrator.companyLongitude;
    } else if (this.systemAdministrator) {
      companyLatitude = this.systemAdministrator.companyLatitude;
      companyLongitude = this.systemAdministrator.companyLongitude;
    }

    // REFERENCE: https://developer.tomtom.com/blog/build-different/using-tomtom-maps-sdk-angular-and-typescript/
    // REFERENCE: https://api.tomtom.com/maps-sdk-for-web/6.x/6.25.0/documentation/dist/classes/Maps.Map.html
    this.map = tt.map({
      key: 'KAOC1hoUVJRq4FbOszG5lblOclvAYkFS',
      container: 'mapDiv',
      center: [companyLongitude, companyLatitude],
      zoom: 15
    }).addControl(
        new tt.NavigationControl({ showZoom: true, showCompass: false, showExtendedRotationControls: false }), 
        'bottom-right');
    this.marker = new tt.Marker({ draggable: false }).setLngLat([companyLongitude, companyLatitude]).addTo(this.map);
  }
}
