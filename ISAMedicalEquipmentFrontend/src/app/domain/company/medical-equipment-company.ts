export class MedicalEquipmentCompany {
    id: number;
    name: string;
    address: string;
    description: string;
    averageGrade: number;
    workTime: string;

    constructor(id: number, name: string, address: string, description: string, averageGrade: number, workTime: string) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.averageGrade = averageGrade;
        this.workTime = workTime;
    }
}
