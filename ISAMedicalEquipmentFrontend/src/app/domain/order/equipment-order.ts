import { DetailsOfEquipmentOrder } from "./details-of-equipment-order";

export class EquipmentOrder {
    id: number;
    exchangeTermId: number;
    procurementManagerId: number;
    details: DetailsOfEquipmentOrder[];
    totalPrice: number;

    constructor(id: number, exchangeTermId: number, procurementManagerId: number, details: DetailsOfEquipmentOrder[], 
            totalPrice: number) {
        this.id = id;
        this.exchangeTermId = exchangeTermId;
        this.procurementManagerId = procurementManagerId;
        this.details = details;
        this.totalPrice = totalPrice;
    }
}
