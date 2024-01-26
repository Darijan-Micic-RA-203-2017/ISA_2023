import { DateTime } from "luxon";

export class ExchangeTerm {
    id: number;
    startingTime: DateTime;
    endingTime: DateTime;
    procurementManagerId: number;
    companyId: number;
    administratorId: number;

    constructor(id: number, startingTime: DateTime, endingTime: DateTime, procurementManagerId: number, 
            companyId: number, administratorId: number) {
        this.id = id;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
        this.procurementManagerId = procurementManagerId;
        this.companyId = companyId;
        this.administratorId = administratorId;
    }
}
