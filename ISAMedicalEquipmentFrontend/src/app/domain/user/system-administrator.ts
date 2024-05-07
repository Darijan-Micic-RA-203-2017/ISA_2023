import { DateTime } from "luxon";

import { User } from "./user";
import { UserRole } from "./user-role";

export class SystemAdministrator extends User {
    employedSince: DateTime;

    constructor(id: number, roles: UserRole[], isEnabled: boolean, userCode: string, emailAddress: string, 
            username: string, password: string | null, lastPasswordResetDate: number | DateTime | null, 
            firstName: string, lastName: string, residence: string, populatedPlace: string, country: string, 
            phoneNumber: string, personalIdentityNumber: string, gender: string, profession: string | null, 
            companyName: string | null, employedSince: number | DateTime) {
        super(id, roles, isEnabled, userCode, emailAddress, username, password, lastPasswordResetDate, firstName, 
                lastName, residence, populatedPlace, country, phoneNumber, personalIdentityNumber, gender, profession, 
                companyName);

        if (DateTime.isDateTime(employedSince)) {
            this.employedSince = employedSince;
        } else {
            this.employedSince = DateTime.fromMillis(employedSince);
        }
    }
}
