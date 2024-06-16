package ftn.project.ISAMedicalEquipmentBackend.kafka;

import ftn.project.ISAMedicalEquipmentBackend.dto.kafka.LocationDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.kafka.LocationWithUserDTO;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

/** REFERENCE: https://github.com/ivana-k/isa-vezbe/tree/main/Vezbe08/kafka-consumer-example **/
@Component
public class LocationConsumer {
	private final SimpMessagingTemplate simpMessagingTemplate;
	
	@Autowired
	public LocationConsumer(SimpMessagingTemplate simpMessagingTemplate) {
		this.simpMessagingTemplate = simpMessagingTemplate;
	}
	
	@KafkaListener(autoStartup = "false", id = "locationsSimulatorListener", 
		topics = "locations-simulator", groupId = "locations-simulator", 
		containerFactory = "kafkaListenerContainerFactory")
    public void listenToLocationsSimulatorForLocations(String message) {
		try {
			// REFERENCE: https://www.baeldung.com/jackson-object-mapper-tutorial
			ObjectMapper objectMapper = new ObjectMapper();
			LocationWithUserDTO locationWithUserDTO = 
					objectMapper.readValue(message, LocationWithUserDTO.class);
			LocationDTO locationDTO = new LocationDTO(locationWithUserDTO.getLatitude(), 
					locationWithUserDTO.getLongitude());
			
			simpMessagingTemplate.convertAndSendToUser(locationWithUserDTO.getUser(), 
					"/queue/locations-simulator", locationDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
