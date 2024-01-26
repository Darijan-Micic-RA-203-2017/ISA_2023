import { CdkTextareaAutosize } from '@angular/cdk/text-field';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, NgZone, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { take } from 'rxjs/operators';

import { AuthService } from 'src/app/services/auth/auth.service';
import { MedicalEquipmentCompanyService } from 'src/app/services/medical-equipment-company/medical-equipment-company.service';
import { MedicalEquipmentService } from 'src/app/services/medical-equipment/medical-equipment.service';
import { ExchangeTermService } from 'src/app/services/exchange-term/exchange-term.service';

import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';

import { DateTime } from 'luxon';
import { MedicalEquipmentCompany } from 'src/app/domain/company/medical-equipment-company';
import { MedicalEquipment } from 'src/app/domain/equipment/medical-equipment';
import { DateTimeWrapper } from 'src/app/domain/date-time-wrapper';
import { ExchangeTerm } from 'src/app/domain/term/exchange-term';

@Component({
  selector: 'app-company-profile',
  templateUrl: './company-profile.component.html',
  styleUrls: ['./company-profile.component.css']
})
export class CompanyProfileComponent implements OnInit {
  formForCompany: any;
  isEditCompanyButtonHidden: boolean = false;
  isFormForCompanyDisabled: boolean = true;
  isFormForCompanySubmitted: boolean = false;

  company: MedicalEquipmentCompany = {
    id: 0,
    name: '',
    streetAndNumber: '',
    populatedPlace: '',
    country: '',
    description: '',
    averageGrade: 0.0,
    workTime: {
      id: 0, 
      onMondaysThroughFridays: '7:30 - 21:30', 
      onSaturdays: '7:30 - 21:30', 
      onSundays: null
    }
  };

  displayedColumnsOfEquipment: string[] = ['name', 'type', 'price'];
  medicalEquipmentOfCompany: MedicalEquipment[] = [];
  equipmentDataSource: MatTableDataSource<MedicalEquipment> = 
      new MatTableDataSource<MedicalEquipment>(this.medicalEquipmentOfCompany);
  
  formForTerm: any;
  minDate: DateTime = DateTime.now().set({ hour: 0, minute: 0, second: 0, millisecond: 0 });

  occupiedTermsOnSelectedDate: ExchangeTerm[] = [];
  
  constructor(private authService: AuthService, private medicalEquipmentCompanyService: MedicalEquipmentCompanyService, 
      private medicalEquipmentService: MedicalEquipmentService, private exchangeTermService: ExchangeTermService, 
      private formBuilder: FormBuilder, private ngZone: NgZone, private router: Router, private snackBar: MatSnackBar) { }
  
  ngOnInit(): void {
    this.hideEditCompanyButtonIfUserIsAProcurementManager();
    
    this.fillFormForCompanyWithData();
    this.initializeFormForTerm();
    
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
        this.fillFormForCompanyWithData();

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

        this.determineFreeTermsOnSelectedTermDate();
      },
      (errorResponse: HttpErrorResponse) => {
        console.log('Error on retrieving medical equipment company by id!', errorResponse.error.textMessage);
        this.snackBar.open('Kompanija nije mogla biti dobavljena!', 'Zatvori', { duration: 5000 });
      }
    );
  }

  hideEditCompanyButtonIfUserIsAProcurementManager(): void {
    if (this.authService.isUserAProcurementManager()) {
      this.isEditCompanyButtonHidden = true;
    }
  }

  fillFormForCompanyWithData(): void {
    if (this.company.id == 0) {
      this.formForCompany = this.formBuilder.group({
        name: new FormControl({value: this.company.name, disabled: this.isFormForCompanyDisabled}, {
          validators: [Validators.required, Validators.pattern(/^[A-Z\p{L}][a-z\p{L}]+([ -][A-Z\p{L}][a-z\p{L}]+)*$/u)], 
          updateOn: 'change'
        }),
        streetAndNumber: new FormControl({value: this.company.streetAndNumber, disabled: this.isFormForCompanyDisabled}, {
          validators: [Validators.required, Validators.pattern(/^[A-Z\p{L}][a-z\p{L}]+([ -][A-Z\p{L}][a-z\p{L}]+)*$/u)], 
          updateOn: 'change'
        }),
        populatedPlace: new FormControl({value: this.company.populatedPlace, disabled: this.isFormForCompanyDisabled}, {
          validators: [Validators.required, Validators.pattern(/^[A-Z\p{L}][a-z\p{L}]+([ -][A-Z\p{L}][a-z\p{L}]+)*$/u)], 
          updateOn: 'change'
        }),
        country: new FormControl({value: this.company.country, disabled: this.isFormForCompanyDisabled}, {
          validators: [Validators.required, Validators.pattern(/^[A-Z\p{L}][a-z\p{L}]+([ -][A-Z\p{L}][a-z\p{L}]+)*$/u)], 
          updateOn: 'change'
        }),
        workTime: new FormControl({value: this.company.workTime, disabled: this.isFormForCompanyDisabled}, {
          validators: [Validators.required], 
          updateOn: 'change'
        }),
        description: new FormControl({value: this.company.description, disabled: this.isFormForCompanyDisabled}, {
          validators: [Validators.required], 
          updateOn: 'change'
        })
      });
    } else {
      // REFERENCE: https://stackoverflow.com/a/55275042
      this.formForCompany.setValue({
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

  initializeFormForTerm(): void {
    this.formForTerm = this.formBuilder.group({
      termDate: new FormControl(this.minDate, {
        validators: [Validators.required], 
        updateOn: 'change'
      })
    });
  }

  // KOD ZA ODREDJIVANJE KOJI 30-MINUTNI TERMINI CE BITI PRIKAZANI, NA OSNOVU RADNOG VREMENA
  // KOMPANIJE NA TAJ DAN.
  determineFreeTermsOnSelectedTermDate(): void {
    this.exchangeTermService.findAllOnSpecificDateOfCompany(
        new DateTimeWrapper(this.formForTerm.value.termDate), this.company.id).subscribe(
      data => {
        console.log('Retrieving all exchange terms on selected date of company response: ', data);

        this.occupiedTermsOnSelectedDate = data;
      },
      (errorResponse: HttpErrorResponse) => {
        console.log('Error on retrieving all exchange terms on selected date of company!', 
            errorResponse.error.textMessage);
        this.snackBar.open('Termini kompanije zakazani odabranog datuma nisu mogli biti dobavljeni!', 'Zatvori', 
            { duration: 5000 });
      }
    );
  }

  scheduleTerm(): void {
    this.snackBar.open('Zakazivanje termina će uskoro biti odrađeno.', 'Zatvori', { duration: 5000 });
  }

  getErrorMessageFor(data: string): string {
    let errorMessage: string = '';

    switch (data) {
      case 'name':
        if (this.formForCompany.get('name').hasError('required')) {
          errorMessage = 'Morate uneti ime kompanije!';
        }

        if (this.formForCompany.get('name').hasError('pattern')) {
          errorMessage = 'Ime kompanije mora početi velikim slovom i završiti se slovom ili tačkom!\n' + 
              'Dozvoljeni su znakovi \'_\', \'-\' i \'.\'.';
        }

        break;
      case 'streetAndNumber':
        if (this.formForCompany.get('streetAndNumber').hasError('required')) {
          errorMessage = 'Morate uneti ulicu i broj!';
        }
        
        if (this.formForCompany.get('streetAndNumber').hasError('pattern')) {
          errorMessage = 'Naziv ulice mora početi velikim slovom!';
        }
        
        break;
      case 'populatedPlace':
        if (this.formForCompany.get('populatedPlace').hasError('required')) {
          errorMessage = 'Morate uneti mesto!';
        }
        
        if (this.formForCompany.get('populatedPlace').hasError('pattern')) {
          errorMessage = 'Naziv mesta mora početi velikim slovom!';
        }
        
        break;
      case 'country':
        if (this.formForCompany.get('country').hasError('required')) {
          errorMessage = 'Morate uneti državu!';
        }
        
        if (this.formForCompany.get('country').hasError('pattern')) {
          errorMessage = 'Naziv države mora početi velikim slovom!';
        }
        
        break;
      case 'workTime':
        if (this.formForCompany.get('workTime').hasError('required')) {
          errorMessage = 'Morate uneti radno vreme!';
        }
        
        break;
      case 'description':
        if (this.formForCompany.get('description').hasError('required')) {
          errorMessage = 'Morate uneti opis kompanije!';
        }
        
        break;
      case 'termDate':
        if (this.formForTerm.get('termDate').hasError('required')) {
          errorMessage = 'Morate odabrati datum termina!';
        }

        if (this.formForTerm.get('termDate').hasError('matDatepickerMin')) {
          errorMessage = 'Termin može biti zakazan samo u budućnosti!';
        }

        break;
      default:
        errorMessage = '';
    }

    return errorMessage;
  }
}
