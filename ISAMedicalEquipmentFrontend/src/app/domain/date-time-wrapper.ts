import { DateTime } from "luxon";

export class DateTimeWrapper {
    dateTime: DateTime;

    constructor(dateTime: DateTime) {
        this.dateTime = dateTime;
    }
}
