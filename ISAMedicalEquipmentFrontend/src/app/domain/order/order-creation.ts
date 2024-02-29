import { ExchangeTerm } from "../term/exchange-term";
import { EquipmentOrder } from "./equipment-order";

export class OrderCreation {
    term: ExchangeTerm;
    order: EquipmentOrder;

    constructor(term: ExchangeTerm, order: EquipmentOrder) {
        this.term = term;
        this.order = order;
    }
}
