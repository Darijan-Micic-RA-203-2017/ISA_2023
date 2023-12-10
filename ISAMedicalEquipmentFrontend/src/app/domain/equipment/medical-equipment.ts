import { MedicalEquipmentCompany } from "../company/medical-equipment-company";
import { TypeOfMedicalEquipment } from "./type-of-medical-equipment";

export class MedicalEquipment {
    id: number;
    type: TypeOfMedicalEquipment;
    name: string;
    price: number;
    amount: number;
    company: MedicalEquipmentCompany;

    constructor(id: number, type: TypeOfMedicalEquipment, name: string, price: number, amount: number, 
            company: MedicalEquipmentCompany) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.company = company;
    }
}
