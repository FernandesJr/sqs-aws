package com.fernandes.developer.demofilasqs.services;

import com.fernandes.developer.demofilasqs.dto.MensagemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class SqsService {

    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;

    @Value("${cloud.aws.end-point.uri}")
    private String endPoint;

    public void sendMensagemToQueue(MensagemDTO dto){
        queueMessagingTemplate.send(endPoint, MessageBuilder.withPayload(dto.msgJson()).build());
    }
}
