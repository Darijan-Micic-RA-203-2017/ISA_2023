import { ExchangeTerm } from "../term/exchange-term";
import { EquipmentOrder } from "./equipment-order";

export class OrderCreation {
    exchangeTerm: ExchangeTerm;
    order: EquipmentOrder;

    constructor(exchangeTerm: ExchangeTerm, order: EquipmentOrder) {
        this.exchangeTerm = exchangeTerm;
        this.order = order;
    }
}
