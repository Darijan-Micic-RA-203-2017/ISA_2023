import { DateTime } from "luxon";

import { User } from "./user";
import { UserRole } from "./user-role";
import { LoyaltyProgram } from "./loyalty-program";
import { ExchangeTerm } from "../term/exchange-term";
import { Complaint } from "../complaint/complaint";

export class ProcurementManagerOfHospital extends User {
    penaltyPoints: number;
    loyaltyPoints: number;
    loyaltyProgram: LoyaltyProgram;
    exchangeTerms: ExchangeTerm[];
    complaints: Complaint[];

    constructor(id: number, roles: UserRole[], isEnabled: boolean, userCode: string, emailAddress: string, 
            username: string, password: string | null, lastPasswordResetDate: number | DateTime | null, 
            firstName: string, lastName: string, residence: string, populatedPlace: string, country: string, 
            phoneNumber: string, personalIdentityNumber: string, gender: string, profession: string | null, 
            companyName: string | null, penaltyPoints: number, loyaltyPoints: number, loyaltyProgram: LoyaltyProgram, 
            exchangeTerms: ExchangeTerm[], complaints: Complaint[]) {
        super(id, roles, isEnabled, userCode, emailAddress, username, password, lastPasswordResetDate, firstName, 
                lastName, residence, populatedPlace, country, phoneNumber, personalIdentityNumber, gender, profession, 
                companyName);

        this.penaltyPoints = penaltyPoints;
        this.loyaltyPoints = loyaltyPoints;
        this.loyaltyProgram = loyaltyProgram;
        this.exchangeTerms = exchangeTerms;
        this.complaints = complaints;
    }
}
