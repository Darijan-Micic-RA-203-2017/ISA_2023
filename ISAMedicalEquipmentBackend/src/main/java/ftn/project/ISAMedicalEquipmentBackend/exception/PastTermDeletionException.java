package ftn.project.ISAMedicalEquipmentBackend.exception;

// REFERENCE: https://www.baeldung.com/java-new-custom-exception
public class PastTermDeletionException extends Exception {
	public PastTermDeletionException() {
		super("Term that is requested to be deleted happened in the past and therefore cannot be deleted!");
	}
}
