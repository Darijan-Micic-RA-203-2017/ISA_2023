export class DetailsOfEquipmentOrder {
    id: number;
    orderId: number;
    equipmentId: number;
    amount: number;
    subtotalPrice: number;

    constructor(id: number, orderId: number, equipmentId: number, amount: number, subtotalPrice: number) {
        this.id = id;
        this.orderId = orderId;
        this.equipmentId = equipmentId;
        this.amount = amount;
        this.subtotalPrice = subtotalPrice;
    }
}
