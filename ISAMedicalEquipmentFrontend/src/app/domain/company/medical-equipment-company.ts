import { WorkTime } from "./work-time";

export class MedicalEquipmentCompany {
    id: number;
    name: string;
    latitude: number;
    longitude: number;
    streetAndNumber: string;
    populatedPlace: string;
    country: string;
    description: string;
    averageGrade: number;
    workTime: WorkTime;

    constructor(id: number, name: string, latitude: number, longitude: number, streetAndNumber: string, 
            populatedPlace: string, country: string, description: string, averageGrade: number, workTime: WorkTime) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.streetAndNumber = streetAndNumber;
        this.populatedPlace = populatedPlace;
        this.country = country;
        this.description = description;
        this.averageGrade = averageGrade;
        this.workTime = workTime;
    }
}
