package ftn.project.ISALocationsSimulator.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class ProducerConfig {
	public ProducerConfig() {}
	
	@Bean
	public KafkaTemplate<String, String> kafkaTemplate() {
		return new KafkaTemplate<String, String>(producerFactory());
	}
	
	@Bean
	public ProducerFactory<String, String> producerFactory() {
		Map<String, Object> configurations = new HashMap<String, Object>();
		
		configurations.put(
				org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, 
				"localhost:9092");
		configurations.put(
				org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, 
				StringSerializer.class);
		configurations.put(
				org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, 
				StringSerializer.class);
		
		return new DefaultKafkaProducerFactory<String, String>(configurations);
    }
}
