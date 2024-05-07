import { DateTime } from "luxon";

export class ExchangeTerm {
    id: number;
    startingTime: DateTime;
    endingTime: DateTime;
    procurementManagerId: number;
    companyId: number;
    companyAdministratorId: number;

    constructor(id: number, startingTime: number | DateTime, endingTime: number | DateTime, 
            procurementManagerId: number, companyId: number, companyAdministratorId: number) {
        this.id = id;
        if (DateTime.isDateTime(startingTime)) {
            this.startingTime = startingTime;
        } else {
            this.startingTime = DateTime.fromMillis(startingTime);
        }
        if (DateTime.isDateTime(endingTime)) {
            this.endingTime = endingTime;
        } else {
            this.endingTime = DateTime.fromMillis(endingTime);
        }
        this.procurementManagerId = procurementManagerId;
        this.companyId = companyId;
        this.companyAdministratorId = companyAdministratorId;
    }
}
