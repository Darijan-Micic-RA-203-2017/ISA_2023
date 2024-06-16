import { DateTime } from "luxon";

import { UserRole } from "./user-role";

export abstract class User {
    // REFERENCE: https://stackoverflow.com/questions/48733012/spring-requestbody-inheritance
    // REFERENCE: https://stackoverflow.com/questions/27170298/spring-reponsebody-requestbody-with-abstract-class?rq=3
    type: string | undefined;
    id: number;
    roles: UserRole[];
    isEnabled: boolean;
    userCode: string;
    emailAddress: string;
    username: string;
    password: string | null;
    lastPasswordResetDate: DateTime | null;
    firstName: string;
    lastName: string;
    residence: string;
    populatedPlace: string;
    country: string;
    phoneNumber: string;
    personalIdentityNumber: string;
    gender: string;
    profession: string | null;
    companyName: string | null;
    companyLatitude: number;
    companyLongitude: number;

    constructor(type: string | undefined, id: number, roles: UserRole[], isEnabled: boolean, userCode: string, 
            emailAddress: string, username: string, password: string | null, 
            lastPasswordResetDate: number | DateTime | null, firstName: string, lastName: string, residence: string, 
            populatedPlace: string, country: string, phoneNumber: string, personalIdentityNumber: string, gender: string, 
            profession: string | null, companyName: string | null, companyLatitude: number, companyLongitude: number) {
        this.type = type;
        this.id = id;
        this.roles = roles;
        this.isEnabled = isEnabled;
        this.userCode = userCode;
        this.emailAddress = emailAddress;
        this.username = username;
        this.password = password;
        if (DateTime.isDateTime(lastPasswordResetDate)) {
            this.lastPasswordResetDate = lastPasswordResetDate;
        } else if (lastPasswordResetDate) {
            this.lastPasswordResetDate = DateTime.fromMillis(lastPasswordResetDate);
        } else {
            this.lastPasswordResetDate = null;
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.residence = residence;
        this.populatedPlace = populatedPlace;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.personalIdentityNumber = personalIdentityNumber;
        this.gender = gender;
        this.profession = profession;
        this.companyName = companyName;
        this.companyLatitude = companyLatitude;
        this.companyLongitude = companyLongitude;
    }
}
