import { DateTime } from "luxon";
import { ExchangeTerm } from "../term/exchange-term";
import { LoyaltyProgram } from "./loyalty-program";
import { User } from "./user";
import { UserRole } from "./user-role";

export class CompanyAdministrator extends User {
    companyId: number;
    penaltyPoints: number;
    loyaltyPoints: number;
    loyaltyProgram: LoyaltyProgram;
    exchangeTerms: ExchangeTerm[];

    constructor(id: number, roles: UserRole[], isEnabled: boolean, userCode: string, emailAddress: string, 
            username: string, password: string | undefined, lastPasswordResetDate: number | DateTime | null, 
            firstName: string, lastName: string, residence: string, populatedPlace: string, country: string, 
            phoneNumber: string, personalIdentityNumber: string, gender: string, profession: string | null, 
            companyName: string | null, companyId: number, penaltyPoints: number, loyaltyPoints: number, 
            loyaltyProgram: LoyaltyProgram, exchangeTerms: ExchangeTerm[]) {
        super(id, roles, isEnabled, userCode, emailAddress, username, password, lastPasswordResetDate, firstName, 
                lastName, residence, populatedPlace, country, phoneNumber, personalIdentityNumber, gender, profession, 
                companyName);

        this.companyId = companyId;
        this.penaltyPoints = penaltyPoints;
        this.loyaltyPoints = loyaltyPoints;
        this.loyaltyProgram = loyaltyProgram;
        this.exchangeTerms = exchangeTerms;
    }
}
