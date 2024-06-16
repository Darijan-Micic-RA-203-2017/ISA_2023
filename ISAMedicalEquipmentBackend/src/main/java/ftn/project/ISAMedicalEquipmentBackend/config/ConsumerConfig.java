package ftn.project.ISAMedicalEquipmentBackend.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@EnableKafka
@Configuration
public class ConsumerConfig {
	public ConsumerConfig() {}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = 
				new ConcurrentKafkaListenerContainerFactory<String, String>();
		factory.setConsumerFactory(consumerFactory());
		
		return factory;
    }
	
	@Bean
	public ConsumerFactory<String, String> consumerFactory() {
		Map<String, Object> configurations = new HashMap<String, Object>();
		
		configurations.put(
				org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, 
				"localhost:9092");
		configurations.put(
				org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG, 
				"0");
		configurations.put(
				org.apache.kafka.clients.consumer.ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, 
				"latest");
		configurations.put(
				org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, 
				StringDeserializer.class);
		configurations.put(
				org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, 
				StringDeserializer.class);
		
		return new DefaultKafkaConsumerFactory<String, String>(configurations);
    }
}
