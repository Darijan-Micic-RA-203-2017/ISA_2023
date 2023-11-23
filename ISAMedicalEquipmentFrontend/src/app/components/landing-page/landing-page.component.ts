import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';

import { MedicalEquipmentCompanyService } from 'src/app/services/medical-equipment-company/medical-equipment-company.service';

import { MatTableDataSource } from '@angular/material/table';

import { MedicalEquipmentCompany } from 'src/app/domain/company/medical-equipment-company';
import { SearchCriterion } from 'src/app/domain/search-criterion';

@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.css']
})
export class LandingPageComponent implements OnInit {
  form: any;

  displayedColumns: string[] = ['name', 'streetAndNumber', 'populatedPlace', 'country', 'averageGrade'];
  allMedicalEquipmentCompanies: MedicalEquipmentCompany[] = [];
  shownMedicalEquipmentCompanies: MedicalEquipmentCompany[] = [];
  dataSource: MatTableDataSource<MedicalEquipmentCompany> = 
      new MatTableDataSource<MedicalEquipmentCompany>(this.shownMedicalEquipmentCompanies);
  
  constructor(private medicalEquipmentCompanyService: MedicalEquipmentCompanyService, private formBuilder: FormBuilder) { }
  
  ngOnInit(): void {
    this.form = this.formBuilder.group({
      searchCriterion: new FormControl('', {
        validators: [Validators.pattern(/^$|^[A-Z\p{L}][a-z\p{L}]+([ -][A-Z\p{L}][a-z\p{L}]+)*$/u)], 
        updateOn: 'change'
      })
    });

    this.medicalEquipmentCompanyService.findAll().subscribe(
      data => {
        console.log('Retrieving all medical equipment companies response: ', data);
        
        this.allMedicalEquipmentCompanies = data;
        this.shownMedicalEquipmentCompanies = data;
        this.dataSource = new MatTableDataSource<MedicalEquipmentCompany>(this.shownMedicalEquipmentCompanies);
      },
      error => {
        console.log('Error on retrieving all medical equipment companies!', error);
      }
    );
  }

  searchCompaniesByNameOrPopulatedPlace(): void {
    if (!this.form.value.searchCriterion) {
      this.shownMedicalEquipmentCompanies = this.allMedicalEquipmentCompanies;
      this.dataSource = new MatTableDataSource<MedicalEquipmentCompany>(this.shownMedicalEquipmentCompanies);

      return;
    }

    const searchCriterion = new SearchCriterion(this.form.value.searchCriterion);

    this.medicalEquipmentCompanyService.searchByNameOrPopulatedPlace(searchCriterion).subscribe(
      data => {
        console.log('Search medical equipment companies by name or populated place response: ', data);

        this.shownMedicalEquipmentCompanies = data;
        this.dataSource = new MatTableDataSource<MedicalEquipmentCompany>(this.shownMedicalEquipmentCompanies);
      },
      error => {
        console.log('Error on search medical equipment companies by name or populated place!', error);
      }
    );
  }
}
