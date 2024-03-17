import { DateTime } from "luxon";
import { UserRole } from "./user-role";

export class User {
    id: number;
    roles: UserRole[];
    isEnabled: boolean;
    userCode: string;
    emailAddress: string;
    username: string;
    password: string | undefined;
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

    constructor(id: number, roles: UserRole[], isEnabled: boolean, userCode: string, emailAddress: string, 
            username: string, password: string | undefined, lastPasswordResetDate: number | DateTime | null, 
            firstName: string, lastName: string, residence: string, populatedPlace: string, country: string, 
            phoneNumber: string, personalIdentityNumber: string, gender: string, profession: string | null, 
            companyName: string | null) {
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
    }
}
