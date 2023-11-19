export class ProcurementManagerRegistrationReq {
    emailAddress: string;
    username: string;
    password: string;
    firstName: string;
    lastName: string;
    residence: string;
    populatedPlace: string;
    country: string;
    phoneNumber: string;
    personalIdentityNumber: string;
    gender: string;
    profession: string | null;
    companyName: string | null;

    constructor(emailAddress: string, username: string, password: string, firstName: string, lastName: string, 
            residence: string, populatedPlace: string, country: string, phoneNumber: string, 
            personalIdentityNumber: string, gender: string, profession: string | null, 
            companyName: string | null) {
        this.emailAddress = emailAddress;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.residence = residence;
        this.populatedPlace = populatedPlace;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.personalIdentityNumber = personalIdentityNumber;
        this.gender = gender;
        this.profession = profession;
        this.companyName = companyName;
    }
}
