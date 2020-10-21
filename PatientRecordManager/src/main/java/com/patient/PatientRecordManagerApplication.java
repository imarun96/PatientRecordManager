package com.patient;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class PatientRecordManagerApplication {
	public static void main(String[] args) {
		SpringApplication.run(PatientRecordManagerApplication.class, args);
	}

	@Bean
	public MessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public AmqpTemplate template(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(converter());
		return rabbitTemplate;
	}

	/*
	 * @Bean public Map<String, Object> producerConfigs() { Map<String, Object>
	 * props = new HashMap<>(); props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
	 * "localhost:9092"); props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
	 * StringSerializer.class);
	 * props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
	 * JsonSerializer.class); return props; }
	 */

	/*
	 * @Bean public ProducerFactory<String, Object> producerFactory() { return new
	 * DefaultKafkaProducerFactory<>(producerConfigs()); }
	 * 
	 * @Bean public KafkaTemplate<String, Object> kafkaTemplate() { return new
	 * KafkaTemplate<>(producerFactory()); }
	 */
}