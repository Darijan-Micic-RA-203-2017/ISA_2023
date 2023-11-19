import { TypeOfMedicalEquipment } from "./type-of-medical-equipment";

export class MedicalEquipment {
    id: number;
    type: TypeOfMedicalEquipment;
    name: string;
    price: number;
    amount: number;

    constructor(id: number, type: TypeOfMedicalEquipment, name: string, price: number, amount: number) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }
}
