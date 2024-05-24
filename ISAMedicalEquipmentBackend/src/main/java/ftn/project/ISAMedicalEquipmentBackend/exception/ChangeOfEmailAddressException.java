package ftn.project.ISAMedicalEquipmentBackend.exception;

// REFERENCE: https://www.baeldung.com/java-new-custom-exception
public class ChangeOfEmailAddressException extends Exception {
	public ChangeOfEmailAddressException() {
		super("Email address cannot be changed!");
	}
}
