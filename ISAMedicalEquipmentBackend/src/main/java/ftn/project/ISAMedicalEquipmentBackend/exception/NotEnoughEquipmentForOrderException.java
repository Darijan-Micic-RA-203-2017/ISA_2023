package ftn.project.ISAMedicalEquipmentBackend.exception;

// REFERENCE: https://www.baeldung.com/java-new-custom-exception
public class NotEnoughEquipmentForOrderException extends Exception {
	public NotEnoughEquipmentForOrderException() {
		super("There is not enough equipment for order!");
	}
}
