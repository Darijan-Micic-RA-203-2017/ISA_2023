import { MedicalEquipment } from "../equipment/medical-equipment";

/**Helper class for encapsulating equipment data so that it could be shown in equipment table.*/
export class DetailsOfOrderWithEquipment {
    id: number;
    orderId: number;
    equipment: MedicalEquipment;
    amount: number;
    subtotalPrice: number;

    constructor(id: number, orderId: number, equipment: MedicalEquipment, amount: number, subtotalPrice: number) {
        this.id = id;
        this.orderId = orderId;
        this.equipment = equipment;
        this.amount = amount;
        this.subtotalPrice = subtotalPrice;
    }
}
