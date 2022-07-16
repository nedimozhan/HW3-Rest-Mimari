package com.ned.banking.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.ned.banking.fileoperations.FileWriter;

import org.springframework.kafka.support.KafkaHeaders;


 @Component
public class Consumer {

	@KafkaListener(topics = "logs", groupId = "transfer_consumer_group")
	public void listenTransfer(
			  @Payload String message, 
			  @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition
	) {
	    FileWriter.writeLogToFile(message);
	}
} 

