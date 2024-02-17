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
import { DetailsOfOrderWithEquipment } from 'src/app/domain/order/details-of-order-with-equipment';
import { SearchCriterion } from 'src/app/domain/search-criterion';
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
      onMondaysThroughFridays: '', 
      onSaturdays: null, 
      onSundays: null
    }
  };

  formForEquipmentSearch: any;
  displayedColumnsOfEquipment: string[] = ['name', 'type', 'price', 'amount', 'subtotalPrice'];
  allMedicalEquipmentOfCompany: MedicalEquipment[] = [];
  shownMedicalEquipmentOfCompany: MedicalEquipment[] = [];
  shownDetailsOfOrderWithEquipment: DetailsOfOrderWithEquipment[] = [];
  equipmentDataSource: MatTableDataSource<DetailsOfOrderWithEquipment> = 
      new MatTableDataSource<DetailsOfOrderWithEquipment>(this.shownDetailsOfOrderWithEquipment);
  
  formForTerm: any;
  minDate: DateTime = DateTime.now().set({ hour: 0, minute: 0, second: 0, millisecond: 0 });
  occupiedTermsOnSelectedDate: ExchangeTerm[] = [];
  freeTermsOnSelectedDate: ExchangeTerm[] = [];
  
  userId: number = 0;
  
  constructor(private authService: AuthService, private medicalEquipmentCompanyService: MedicalEquipmentCompanyService, 
      private medicalEquipmentService: MedicalEquipmentService, private exchangeTermService: ExchangeTermService, 
      private formBuilder: FormBuilder, private ngZone: NgZone, private router: Router, private snackBar: MatSnackBar) { }
  
  ngOnInit(): void {
    this.hideEditCompanyButtonIfUserIsAProcurementManager();
    
    this.fillFormForCompanyWithData();
    this.initializeFormForEquipmentSearch();
    this.initializeFormForTerm();
    
    let route: string = this.router.url;
    let routeParts: string[] = route.split('company');
    let companyId: number = Number.parseInt(routeParts[routeParts.length - 1].substring(1));
    if (isNaN(companyId)) {
      this.router.navigateByUrl('/');
      
      return;
    }

    this.userId = this.authService.getUserId();
    this.medicalEquipmentCompanyService.findById(companyId).subscribe(
      data => {
        console.log('Retrieving medical equipment company by id response: ', data);

        this.company = data;
        this.fillFormForCompanyWithData();

        this.medicalEquipmentService.findByCompanyName(this.company.name).subscribe(
          data => {
            console.log('Retrieving all medical equipment of company response: ', data);

            this.allMedicalEquipmentOfCompany = data;
            this.shownMedicalEquipmentOfCompany = data;
            for (let equ of this.shownMedicalEquipmentOfCompany) {
              this.shownDetailsOfOrderWithEquipment.push(new DetailsOfOrderWithEquipment(0, 0, equ, 0, 0));
            }
            this.equipmentDataSource = 
                new MatTableDataSource<DetailsOfOrderWithEquipment>(this.shownDetailsOfOrderWithEquipment);
          },
          (errorResponse: HttpErrorResponse) => {
            console.log(`Error on retrieving all medical equipment of company!\n\n${errorResponse.error.textMessage}`);
            this.snackBar.open('Oprema kompanije nije mogla biti dobavljena!', 'Zatvori', { duration: 5000 });
          }
        );

        this.determineFreeTermsOnSelectedTermDate();
      },
      (errorResponse: HttpErrorResponse) => {
        console.log(`Error on retrieving medical equipment company by id!\n\n${errorResponse.error.textMessage}`);
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
        workTimeOnMondaysThroughFridays: new FormControl(
              { value: this.company.workTime.onMondaysThroughFridays, disabled: this.isFormForCompanyDisabled }, {
          validators: [Validators.required, Validators.pattern(/^[0-2][0-9]:[0-5][0-9] - [0-2][0-9]:[0-5][0-9]$/)], 
          updateOn: 'change'
        }),
        workTimeOnSaturdays: new FormControl(
            { value: this.company.workTime.onSaturdays || 'Ne radimo', disabled: this.isFormForCompanyDisabled }, {
          validators: [Validators.pattern(/^$|^Ne radimo$|^[0-2][0-9]:[0-5][0-9] - [0-2][0-9]:[0-5][0-9]$/)], 
          updateOn: 'change'
        }),
        workTimeOnSundays: new FormControl(
            { value: this.company.workTime.onSundays || 'Ne radimo', disabled: this.isFormForCompanyDisabled }, {
          validators: [Validators.pattern(/^$|^Ne radimo$|^[0-2][0-9]:[0-5][0-9] - [0-2][0-9]:[0-5][0-9]$/)], 
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
        workTimeOnMondaysThroughFridays: this.company.workTime.onMondaysThroughFridays,
        workTimeOnSaturdays: this.company.workTime.onSaturdays,
        workTimeOnSundays: this.company.workTime.onSundays,
        description: this.company.description
      });
    }
  }

  // REFERENCE: https://v12.material.angular.io/cdk/text-field/overview
  @ViewChild('descriptionInput') descriptionInput = CdkTextareaAutosize;

  autoResizeTextAreas(): void {
    this.ngZone.onStable.pipe(take(1)).subscribe(() => {
      this.descriptionInput.prototype.resizeToFitContent(true);
    });
  }

  editCompany(): void { }

  initializeFormForEquipmentSearch(): void {
    this.formForEquipmentSearch = this.formBuilder.group({
      searchCriterion: new FormControl('', {
        validators: [Validators.pattern(/^$|^[A-Za-z0-9\p{L}][A-Za-z0-9%\p{L}]+([ -][A-Za-z0-9\p{L}][A-Za-z0-9%\p{L}]+)*$/u)], 
        updateOn: 'change'
      })
    });
  }

  searchEquipmentOfCompanyByName(): void {
    this.shownDetailsOfOrderWithEquipment = [];

    if (!this.formForEquipmentSearch.value.searchCriterion) {
      this.shownMedicalEquipmentOfCompany = this.allMedicalEquipmentOfCompany;
      for (let equ of this.shownMedicalEquipmentOfCompany) {
        this.shownDetailsOfOrderWithEquipment.push(new DetailsOfOrderWithEquipment(0, 0, equ, 0, 0));
      }
      this.equipmentDataSource = 
          new MatTableDataSource<DetailsOfOrderWithEquipment>(this.shownDetailsOfOrderWithEquipment);

      return;
    }

    let searchCriterion: SearchCriterion = new SearchCriterion(this.formForEquipmentSearch.value.searchCriterion);

    this.medicalEquipmentService.searchEquipmentOfCompanyByName(searchCriterion, this.company.name).subscribe(
      data => {
        console.log(`Search medical equipment of company ${this.company.name} by name response: `, data);

        this.shownMedicalEquipmentOfCompany = data;
        for (let equ of this.shownMedicalEquipmentOfCompany) {
          this.shownDetailsOfOrderWithEquipment.push(new DetailsOfOrderWithEquipment(0, 0, equ, 0, 0));
        }
        this.equipmentDataSource = 
            new MatTableDataSource<DetailsOfOrderWithEquipment>(this.shownDetailsOfOrderWithEquipment);
      },
      (errorResponse: HttpErrorResponse) => {
        console.log(`Error on search medical equipment of company ${this.company.name} by name!\n\n${errorResponse.error.textMessage}`);
      }
    );
  }

  // REFERENCE: https://stackoverflow.com/a/57262915
  formatPrice(price: number): number {
    return price % 1 === 0 ? price : Number.parseFloat(price.toFixed(2));
  }

  increaseAmount(details: DetailsOfOrderWithEquipment): void {
    details.amount += 1;

    let newSubtotalPrice: number = details.subtotalPrice + details.equipment.price;
    details.subtotalPrice = this.formatPrice(newSubtotalPrice);
  }

  decreaseAmount(details: DetailsOfOrderWithEquipment): void {
    if (details.amount > 0) {
      details.amount -= 1;

      let newSubtotalPrice: number = details.subtotalPrice - details.equipment.price;
      details.subtotalPrice = this.formatPrice(newSubtotalPrice);
    }
  }

  getTotalPriceOfEquipmentOrder(): number {
    let totalPrice: number = 0;
    for (let details of this.shownDetailsOfOrderWithEquipment) {
      let newTotalPrice: number = totalPrice + details.subtotalPrice;
      totalPrice = this.formatPrice(newTotalPrice);
    }

    return totalPrice;
  }

  initializeFormForTerm(): void {
    this.formForTerm = this.formBuilder.group({
      termDate: new FormControl(this.minDate, {
        validators: [Validators.required], 
        updateOn: 'change'
      }),
      term: new FormControl(null, {
        validators: [Validators.required], 
        updateOn: 'change'
      })
    });
  }

  determineFreeTermsOnSelectedTermDate(): void {
    this.freeTermsOnSelectedDate = [];
    let selectedTermDate: DateTime = this.formForTerm.value.termDate;

    let workTime: string[] = [];
    switch (selectedTermDate.weekday) {
      case 1: case 2: case 3: case 4: case 5:
        workTime = this.company.workTime.onMondaysThroughFridays.split(' - ');

        break;
      case 6:
        if (!this.company.workTime.onSaturdays || this.company.workTime.onSaturdays == 'Ne radimo') {
          break;
        }
        workTime = this.company.workTime.onSaturdays.split(' - ');

        break;
      case 7:
        if (!this.company.workTime.onSundays || this.company.workTime.onSundays == 'Ne radimo') {
          break;
        }
        workTime = this.company.workTime.onSundays.split(' - ');

        break;
      default:
        break;
    }
    if (workTime.length == 0) {
      return;
    }

    this.exchangeTermService.findAllOnSpecificDateOfCompany(
        new DateTimeWrapper(selectedTermDate), this.company.id).subscribe(
      data => {
        console.log('Retrieving all exchange terms on selected date of company response: ', data);
        for (let unconvertedOccupiedTerm of data) {
          let convertedOccupiedTerm: ExchangeTerm = new ExchangeTerm(unconvertedOccupiedTerm.id, 
              DateTime.fromMillis(unconvertedOccupiedTerm.startingTime), 
              DateTime.fromMillis(unconvertedOccupiedTerm.endingTime), unconvertedOccupiedTerm.procurementManagerId, 
              unconvertedOccupiedTerm.companyId, unconvertedOccupiedTerm.administratorId);
          
          this.occupiedTermsOnSelectedDate.push(convertedOccupiedTerm);
        }

        let startOfWorkTime: string[] = workTime[0].split(':');
        let startHourOfWorkTime: number = Number.parseInt(startOfWorkTime[0]);
        let startMinuteOfWorkTime: number = Number.parseInt(startOfWorkTime[1]);
        let endOfWorkTime: string[] = workTime[1].split(':');
        let endHourOfWorkTime: number = Number.parseInt(endOfWorkTime[0]);
        let endMinuteOfWorkTime: number = Number.parseInt(endOfWorkTime[1]);

        let startHourOfCurrentTerm: number = startHourOfWorkTime;
        let startMinuteOfCurrentTerm: number = startMinuteOfWorkTime;
        while (startHourOfCurrentTerm <= endHourOfWorkTime) {
          if (startHourOfCurrentTerm == endHourOfWorkTime && startMinuteOfCurrentTerm == endMinuteOfWorkTime) {
            break;
          }

          let endHourOfCurrentTerm: number = 0;
          let endMinuteOfCurrentTerm: number = 0;
          if (startMinuteOfCurrentTerm == 0) {
            endHourOfCurrentTerm = startHourOfCurrentTerm;
            endMinuteOfCurrentTerm = 30;
          } else {
            endHourOfCurrentTerm = startHourOfCurrentTerm + 1;
            endMinuteOfCurrentTerm = 0;
          }

          let startingTimeAsJSDate: Date = new Date(selectedTermDate.year, selectedTermDate.month - 1, 
              selectedTermDate.day, startHourOfCurrentTerm, startMinuteOfCurrentTerm, 0, 0);
          let startingTime: DateTime = DateTime.fromJSDate(startingTimeAsJSDate);
          let endingTimeAsJSDate: Date = new Date(selectedTermDate.year, selectedTermDate.month - 1, 
              selectedTermDate.day, endHourOfCurrentTerm, endMinuteOfCurrentTerm, 0, 0);
          let endingTime: DateTime = DateTime.fromJSDate(endingTimeAsJSDate);

          let isTermOccupied: boolean = false;
          for (let occupiedTerm of this.occupiedTermsOnSelectedDate) {
            if (occupiedTerm.startingTime.hour == startingTime.hour && 
                  occupiedTerm.startingTime.minute == startingTime.minute) {
              isTermOccupied = true;

              break;
            }
          }
          if (!isTermOccupied) {
            this.freeTermsOnSelectedDate.push(new ExchangeTerm(0, startingTime, endingTime, 
                this.userId, this.company.id, 0));
          }

          startHourOfCurrentTerm = endHourOfCurrentTerm;
          startMinuteOfCurrentTerm = endMinuteOfCurrentTerm;
        }
      },
      (errorResponse: HttpErrorResponse) => {
        console.log(`Error on retrieving all exchange terms on selected date of company!\n\n${errorResponse.error.textMessage}`);
        this.snackBar.open('Termini kompanije zakazani odabranog datuma nisu mogli biti dobavljeni!', 'Zatvori', 
            { duration: 5000 });
      }
    );
  }

  scheduleTerm(): void {
    this.snackBar.open('Zakazivanje termina JE U IZRADI!', 'Nastavi', { duration: 5000 });
    let reservedTerm: ExchangeTerm | null = this.reserveSelectedTerm();
    if (!reservedTerm) {
      return;
    }
  }

  reserveSelectedTerm(): ExchangeTerm | null {
    let selectedTerm: ExchangeTerm = this.formForTerm.value.term;
    let reservedTerm: ExchangeTerm | null = null;

    this.exchangeTermService.reserveTerm(selectedTerm).subscribe(
      data => {
        console.log('Reserving a selected term response: ', data);
        reservedTerm = data.object;
      },
      (errorResponse: HttpErrorResponse) => {
        console.log(`Error on reserving a selected term!\n\n${errorResponse.error.textMessage}`);
      }
    );

    return reservedTerm;
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
      case 'workTimeOnMondaysThroughFridays':
        if (this.formForCompany.get('workTimeOnMondaysThroughFridays').hasError('required')) {
          errorMessage = 'Morate uneti radno vreme od ponedeljka do petka!';
        }

        if (this.formForCompany.get('workTimeOnMondaysThroughFridays').hasError('pattern')) {
          errorMessage = 'Radno vreme mora biti uneto u obliku "hh:mm - hh:mm"!';
        }
        
        break;
      case 'workTimeOnSaturdays':
        if (this.formForCompany.get('workTimeOnSaturdays').hasError('pattern')) {
          errorMessage = 'Mora biti napisano "Ne radimo" ili radno vreme mora biti uneto u obliku "hh:mm - hh:mm"!';
        }
        
        break;
      case 'workTimeOnSundays':
        if (this.formForCompany.get('workTimeOnSundays').hasError('pattern')) {
          errorMessage = 'Mora biti napisano "Ne radimo" ili radno vreme mora biti uneto u obliku "hh:mm - hh:mm"!';
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
      case 'term':
        if (this.formForTerm.get('term').hasError('required')) {
          errorMessage = 'Morate odabrati termin (pritiskom tastera "SPACE")!';
        }
        
        break;
      default:
        errorMessage = '';
    }

    return errorMessage;
  }

  showFreeTerm(freeTerm: ExchangeTerm): string {
    let startHour: string = freeTerm.startingTime.hour.toString();
    if (startHour == '0') {
      startHour = startHour.concat('0');
    }
    let startMinute: string = freeTerm.startingTime.minute.toString();
    if (startMinute == '0') {
      startMinute = startMinute.concat('0');
    }
    let endHour: string = freeTerm.endingTime.hour.toString();
    if (endHour == '0') {
      endHour = endHour.concat('0');
    }
    let endMinute: string = freeTerm.endingTime.minute.toString();
    if (endMinute == '0') {
      endMinute = endMinute.concat('0');
    }
    
    let freeTermAsString: string = startHour.concat(':', startMinute, ' - ', endHour, ':', endMinute);

    return freeTermAsString;
  }
}
