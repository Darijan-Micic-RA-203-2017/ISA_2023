package ftn.project.ISAMedicalEquipmentBackend.exception;

// REFERENCE: https://www.baeldung.com/java-new-custom-exception
public class NotEnoughEquipmentForOrderException extends Exception {
	public NotEnoughEquipmentForOrderException() {
		super("Nema dovoljno opreme za traženu narudžbinu!");
	}
	
	public NotEnoughEquipmentForOrderException(Throwable err) {
		super("Nema dovoljno opreme za traženu narudžbinu!", err);
	}
}
