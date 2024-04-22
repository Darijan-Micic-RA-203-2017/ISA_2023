export class Complaint {
    id: number;
    content: string;
    subject: string;
    status: string;
    answer: string | null;
    procurementManagerId: number;
    companyId: number;
    companyAdministratorId: number;

    constructor(id: number, content: string, subject: string, status: string, answer: string | null, 
            procurementManagerId: number, companyId: number, companyAdministratorId: number) {
        this.id = id;
        this.content = content;
        this.subject = subject;
        this.status = status;
        this.answer = answer;
        this.procurementManagerId = procurementManagerId;
        this.companyId = companyId;
        this.companyAdministratorId = companyAdministratorId;
    }
}
