export class LoyaltyProgram {
    id: number;
    name: string;
    necessaryPoints: number;
    pointsGainedForEachSuccessfulExchange: number;

    constructor(id: number, name: string, necessaryPoints: number, pointsGainedForEachSuccessfulExchange: number) {
        this.id = id;
        this.name = name;
        this.necessaryPoints = necessaryPoints;
        this.pointsGainedForEachSuccessfulExchange = pointsGainedForEachSuccessfulExchange;
    }
}
