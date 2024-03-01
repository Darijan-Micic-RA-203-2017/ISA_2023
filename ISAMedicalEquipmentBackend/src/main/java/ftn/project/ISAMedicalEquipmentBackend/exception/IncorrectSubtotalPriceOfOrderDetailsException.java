package ftn.project.ISAMedicalEquipmentBackend.exception;

//REFERENCE: https://www.baeldung.com/java-new-custom-exception
public class IncorrectSubtotalPriceOfOrderDetailsException extends Exception {
	public IncorrectSubtotalPriceOfOrderDetailsException() {
		super("Specified subtotal price is not correct!");
	}
}
