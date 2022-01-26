package com.data.process.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.data.process.model.ContentRecord;
import com.data.process.util.ApplicationConstant;

@Service
public class LoadDataServiceImpl implements LoadDataService {

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	@Override
 	public String sendDataToKafka() {
 		//Here we need to frame the file records from s3
 		ContentRecord record = new ContentRecord();
		try {
			kafkaTemplate.send(ApplicationConstant.TOPIC_NAME, record);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "json message sent succuessfully";
	}
}
