package com.data.process.config;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import com.data.process.util.ApplicationConstant;

 
@Configuration
@EnableKafka
public class SpringKafkaConfig {

	// Access key id will be read from the application.properties file during the application intialization.
			@Value("${aws.access_key_id}")
			private String accessKeyId;
			// Secret access key will be read from the application.properties file during the application intialization.
			@Value("${aws.secret_access_key}")
			private String secretAccessKey;
			// Region will be read from the application.properties file  during the application intialization.
			@Value("${aws.s3.region}")
			private String region;
			
	@Bean
	public ProducerFactory<String, Object> producerFactory() {
		Map<String, Object> configMap = new HashMap<>();
		configMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, ApplicationConstant.KAFKA_LOCAL_SERVER_CONFIG);
		configMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		configMap.put(JsonDeserializer.TRUSTED_PACKAGES, "com.data.process.dto");
		return new DefaultKafkaProducerFactory<String, Object>(configMap);
	}

	@Bean
	public KafkaTemplate<String, Object> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}

	

		@Bean
		public AmazonS3 getAmazonS3Cient() {
			final BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(accessKeyId, secretAccessKey);
			// Get AmazonS3 client and return the s3Client object.
			return AmazonS3ClientBuilder
					.standard()
					.withRegion(Regions.fromName(region))
					.withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
					.build();
		}
	 
}