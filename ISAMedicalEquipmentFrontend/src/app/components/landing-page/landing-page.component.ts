import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';

import { MedicalEquipmentCompanyService } from 'src/app/services/medical-equipment-company/medical-equipment-company.service';
import { MedicalEquipmentService } from 'src/app/services/medical-equipment/medical-equipment.service';

import { MatTableDataSource } from '@angular/material/table';

import { MedicalEquipmentCompany } from 'src/app/domain/company/medical-equipment-company';
import { MedicalEquipment } from 'src/app/domain/equipment/medical-equipment';
import { SearchCriterion } from 'src/app/domain/search-criterion';

@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.css']
})
export class LandingPageComponent implements OnInit {
  companyForm: any;
  equipmentForm: any;

  displayedColumnsOfCompany: string[] = ['name', 'streetAndNumber', 'populatedPlace', 'country', 'averageGrade'];
  allMedicalEquipmentCompanies: MedicalEquipmentCompany[] = [];
  shownMedicalEquipmentCompanies: MedicalEquipmentCompany[] = [];
  companiesDataSource: MatTableDataSource<MedicalEquipmentCompany> = 
      new MatTableDataSource<MedicalEquipmentCompany>(this.shownMedicalEquipmentCompanies);
  displayedColumnsOfEquipment: string[] = ['name', 'type', 'companyName'];
  allMedicalEquipment: MedicalEquipment[] = [];
  shownMedicalEquipment: MedicalEquipment[] = [];
  equipmentDataSource: MatTableDataSource<MedicalEquipment> = 
      new MatTableDataSource<MedicalEquipment>(this.shownMedicalEquipment);

  constructor(private medicalEquipmentCompanyService: MedicalEquipmentCompanyService, 
      private medicalEquipmentService: MedicalEquipmentService, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.companyForm = this.formBuilder.group({
      searchCriterion: new FormControl('', {
        validators: [Validators.pattern(/^$|^[A-Z\p{L}][a-z\p{L}]+([ -][A-Z\p{L}][a-z\p{L}]+)*$/u)], 
        updateOn: 'change'
      })
    });
    this.equipmentForm = this.formBuilder.group({
      searchCriterion: new FormControl('', {
        validators: [Validators.pattern(/^$|^[A-Za-z0-9\p{L}][A-Za-z0-9%\p{L}]+([ -][A-Za-z0-9\p{L}][A-Za-z0-9%\p{L}]+)*$/u)], 
        updateOn: 'change'
      })
    });

    this.medicalEquipmentCompanyService.findAll().subscribe(
      data => {
        console.log('Retrieving all medical equipment companies response: ', data);

        this.allMedicalEquipmentCompanies = data;
        this.shownMedicalEquipmentCompanies = data;
        this.companiesDataSource.data = this.shownMedicalEquipmentCompanies;
      },
      (errorResponse: HttpErrorResponse) => {
        console.log(`Error on retrieving all medical equipment companies!\n\n${errorResponse.error.textMessage}`);
      }
    );
    this.medicalEquipmentService.findAll().subscribe(
      data => {
        console.log('Retrieving all medical equipment response: ', data);

        this.allMedicalEquipment = data;
        this.shownMedicalEquipment = data;
        this.equipmentDataSource.data = this.shownMedicalEquipment;
      },
      (errorResponse: HttpErrorResponse) => {
        console.log(`Error on retrieving all medical equipment!\n\n${errorResponse.error.textMessage}`);
      }
    );
  }

  searchCompaniesByNameOrPopulatedPlace(): void {
    if (!this.companyForm.value.searchCriterion) {
      this.shownMedicalEquipmentCompanies = this.allMedicalEquipmentCompanies;
      this.companiesDataSource.data = this.shownMedicalEquipmentCompanies;

      return;
    }

    const searchCriterion = new SearchCriterion(this.companyForm.value.searchCriterion);

    this.medicalEquipmentCompanyService.searchByNameOrPopulatedPlace(searchCriterion).subscribe(
      data => {
        console.log('Search medical equipment companies by name or populated place response: ', data);

        this.shownMedicalEquipmentCompanies = data;
        this.companiesDataSource.data = this.shownMedicalEquipmentCompanies;
      },
      (errorResponse: HttpErrorResponse) => {
        console.log(`Error on search medical equipment companies by name or populated place!\n\n${errorResponse.error.textMessage}`);
      }
    );
  }

  searchEquipmentByName(): void {
    if (!this.equipmentForm.value.searchCriterion) {
      this.shownMedicalEquipment = this.allMedicalEquipment;
      this.equipmentDataSource.data = this.shownMedicalEquipment;

      return;
    }

    const searchCriterion = new SearchCriterion(this.equipmentForm.value.searchCriterion);

    this.medicalEquipmentService.searchEquipmentByName(searchCriterion).subscribe(
      data => {
        console.log('Search medical equipment by name response: ', data);

        this.shownMedicalEquipment = data;
        this.equipmentDataSource.data = this.shownMedicalEquipment;
      },
      (errorResponse: HttpErrorResponse) => {
        console.log(`Error on search medical equipment by name!\n\n${errorResponse.error.textMessage}`);
      }
    );
  }
}
