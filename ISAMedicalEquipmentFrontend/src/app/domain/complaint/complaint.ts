import { DateTime } from "luxon";

export class Complaint {
    id: number;
    submittedAt: DateTime;
    content: string;
    subject: string;
    status: string;
    answer: string | null;
    answeredAt: DateTime | null;
    procurementManagerId: number;
    companyId: number;
    companyAdministratorId: number;

    constructor(id: number, submittedAt: number | DateTime, content: string, subject: string, status: string, 
            answer: string | null, answeredAt: number | DateTime | null, procurementManagerId: number, 
            companyId: number, companyAdministratorId: number) {
        this.id = id;
        if (DateTime.isDateTime(submittedAt)) {
            this.submittedAt = submittedAt;
        } else {
            this.submittedAt = DateTime.fromMillis(submittedAt);
        }
        this.content = content;
        this.subject = subject;
        this.status = status;
        this.answer = answer;
        if (DateTime.isDateTime(answeredAt)) {
            this.answeredAt = answeredAt;
        } else if (answeredAt) {
            this.answeredAt = DateTime.fromMillis(answeredAt);
        } else {
            this.answeredAt = null;
        }
        this.procurementManagerId = procurementManagerId;
        this.companyId = companyId;
        this.companyAdministratorId = companyAdministratorId;
    }
}
