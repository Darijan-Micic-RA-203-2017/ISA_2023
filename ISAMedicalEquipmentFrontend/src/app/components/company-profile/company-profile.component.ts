import { CdkTextareaAutosize } from '@angular/cdk/text-field';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, NgZone, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { take } from 'rxjs/operators';

import { AuthService } from 'src/app/services/auth/auth.service';
import { MedicalEquipmentCompanyService } from 'src/app/services/medical-equipment-company/medical-equipment-company.service';
import { MedicalEquipmentService } from 'src/app/services/medical-equipment/medical-equipment.service';

import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';

import { MedicalEquipmentCompany } from 'src/app/domain/company/medical-equipment-company';
import { MedicalEquipment } from 'src/app/domain/equipment/medical-equipment';

@Component({
  selector: 'app-company-profile',
  templateUrl: './company-profile.component.html',
  styleUrls: ['./company-profile.component.css']
})
export class CompanyProfileComponent implements OnInit {
  form: any;
  isHidden: boolean = false;
  isDisabled: boolean = true;
  isSubmitted: boolean = false;

  company: MedicalEquipmentCompany = {
    id: 0,
    name: '',
    streetAndNumber: '',
    populatedPlace: '',
    country: '',
    description: '',
    averageGrade: 0.0,
    workTime: ''
  };

  displayedColumnsOfEquipment: string[] = ['name', 'type', 'price'];
  medicalEquipmentOfCompany: MedicalEquipment[] = [];
  equipmentDataSource: MatTableDataSource<MedicalEquipment> = 
      new MatTableDataSource<MedicalEquipment>(this.medicalEquipmentOfCompany);
  
  constructor(private authService: AuthService, private medicalEquipmentCompanyService: MedicalEquipmentCompanyService, 
      private medicalEquipmentService: MedicalEquipmentService, private formBuilder: FormBuilder, 
      private ngZone: NgZone, private router: Router, private snackBar: MatSnackBar) { }
  
  ngOnInit(): void {
    this.hideEditButtonIfUserIsAProcurementManager();
    
    this.fillFormWithCompanyData();
    
    let route: string = this.router.url;
    let routeParts: string[] = route.split('company');
    let companyId: number = Number.parseInt(routeParts[routeParts.length - 1].substring(1));
    if (isNaN(companyId)) {
      this.router.navigateByUrl('/');
      
      return;
    }
    
    this.medicalEquipmentCompanyService.findById(companyId).subscribe(
      data => {
        console.log('Retrieving medical equipment company by id response: ', data);

        this.company = data;
        this.fillFormWithCompanyData();

        this.medicalEquipmentService.findAllOfCompany(companyId).subscribe(
          data => {
            console.log('Retrieving all medical equipment of company response: ', data);

            this.medicalEquipmentOfCompany = data;
            this.equipmentDataSource = new MatTableDataSource<MedicalEquipment>(this.medicalEquipmentOfCompany);
          },
          (errorResponse: HttpErrorResponse) => {
            console.log('Error on retrieving all medical equipment of company!', errorResponse.error.textMessage);
            this.snackBar.open('Oprema kompanije nije mogla biti dobavljena!', 'Zatvori', { duration: 5000 });
          }
        );
      },
      (errorResponse: HttpErrorResponse) => {
        console.log('Error on retrieving medical equipment company by id!', errorResponse.error.textMessage);
        this.snackBar.open('Kompanija nije mogla biti dobavljena!', 'Zatvori', { duration: 5000 });
      }
    );
  }

  hideEditButtonIfUserIsAProcurementManager(): void {
    if (this.authService.isUserACompanyAdministrator()) {
      this.isHidden = true;
    }
  }

  fillFormWithCompanyData(): void {
    if (this.company.id == 0) {
      this.form = this.formBuilder.group({
        name: new FormControl({value: this.company.name, disabled: this.isDisabled}, {
          validators: [Validators.required, Validators.pattern(/^[A-Z\p{L}][a-z\p{L}]+([ -][A-Z\p{L}][a-z\p{L}]+)*$/u)], 
          updateOn: 'change'
        }),
        streetAndNumber: new FormControl({value: this.company.streetAndNumber, disabled: this.isDisabled}, {
          validators: [Validators.required, Validators.pattern(/^[A-Z\p{L}][a-z\p{L}]+([ -][A-Z\p{L}][a-z\p{L}]+)*$/u)], 
          updateOn: 'change'
        }),
        populatedPlace: new FormControl({value: this.company.populatedPlace, disabled: this.isDisabled}, {
          validators: [Validators.required, Validators.pattern(/^[A-Z\p{L}][a-z\p{L}]+([ -][A-Z\p{L}][a-z\p{L}]+)*$/u)], 
          updateOn: 'change'
        }),
        country: new FormControl({value: this.company.country, disabled: this.isDisabled}, {
          validators: [Validators.required, Validators.pattern(/^[A-Z\p{L}][a-z\p{L}]+([ -][A-Z\p{L}][a-z\p{L}]+)*$/u)], 
          updateOn: 'change'
        }),
        workTime: new FormControl({value: this.company.workTime, disabled: this.isDisabled}, {
          validators: [Validators.required], 
          updateOn: 'change'
        }),
        description: new FormControl({value: this.company.description, disabled: this.isDisabled}, {
          validators: [Validators.required], 
          updateOn: 'change'
        })
      });
    } else {
      // REFERENCE: https://stackoverflow.com/a/55275042
      this.form.setValue({
        name: this.company.name,
        streetAndNumber: this.company.streetAndNumber,
        populatedPlace: this.company.populatedPlace,
        country: this.company.country,
        workTime: this.company.workTime,
        description: this.company.description
      });
    }
  }

  // REFERENCE: https://v12.material.angular.io/cdk/text-field/overview
  @ViewChild('workTimeInput') workTimeInput = CdkTextareaAutosize;
  @ViewChild('descriptionInput') descriptionInput = CdkTextareaAutosize;

  autoResizeTextAreas(): void {
    this.ngZone.onStable.pipe(take(1)).subscribe(() => {
      this.workTimeInput.prototype.resizeToFitContent(true);
      this.descriptionInput.prototype.resizeToFitContent(true);
    });
  }

  editCompany(): void { }

  getErrorMessageFor(data: string): string {
    let errorMessage: string = '';

    switch (data) {
      case 'name':
        if (this.form.get('name').hasError('required')) {
          errorMessage = 'Morate uneti ime kompanije!';
        }

        if (this.form.get('name').hasError('pattern')) {
          errorMessage = 'Ime kompanije mora početi velikim slovom i završiti se slovom ili tačkom!\n' + 
              'Dozvoljeni su znakovi \'_\', \'-\' i \'.\'.';
        }

        break;
      case 'streetAndNumber':
        if (this.form.get('streetAndNumber').hasError('required')) {
          errorMessage = 'Morate uneti ulicu i broj!';
        }
        
        if (this.form.get('streetAndNumber').hasError('pattern')) {
          errorMessage = 'Naziv ulice mora početi velikim slovom!';
        }
        
        break;
      case 'populatedPlace':
        if (this.form.get('populatedPlace').hasError('required')) {
          errorMessage = 'Morate uneti mesto!';
        }
        
        if (this.form.get('populatedPlace').hasError('pattern')) {
          errorMessage = 'Naziv mesta mora početi velikim slovom!';
        }
        
        break;
      case 'country':
        if (this.form.get('country').hasError('required')) {
          errorMessage = 'Morate uneti državu!';
        }
        
        if (this.form.get('country').hasError('pattern')) {
          errorMessage = 'Naziv države mora početi velikim slovom!';
        }
        
        break;
      case 'workTime':
        if (this.form.get('workTime').hasError('required')) {
          errorMessage = 'Morate uneti radno vreme!';
        }
        
        break;
      case 'description':
        if (this.form.get('description').hasError('required')) {
          errorMessage = 'Morate uneti opis kompanije!';
        }
        
        break;
      default:
        errorMessage = '';
    }

    return errorMessage;
  }

  scheduleTerm(): void {
    this.snackBar.open('Zakazivanje termina će uskoro biti odrađeno.', 'Zatvori', { duration: 5000 });
  }
}
