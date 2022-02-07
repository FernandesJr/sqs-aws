package com.fernandes.developer.demofilasqs.services;

import com.fernandes.developer.demofilasqs.dto.MensagemDTO;
import com.fernandes.developer.demofilasqs.entities.Mensagem;
import com.fernandes.developer.demofilasqs.repositories.MensagemRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SqsService {

    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;

    @Value("${cloud.aws.end-point.uri}")
    private String endPoint;

    @Autowired
    private MensagemRepository mensagemRepository;

    public void sendMensagemToQueue(MensagemDTO dto){
        queueMessagingTemplate.send(endPoint, MessageBuilder.withPayload(dto.msgJson()).build());
    }

    @SqsListener(value = "filaTest", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void listenQueue(String message){
        try{
            Mensagem mensagem = new Gson().fromJson(message, Mensagem.class);
            saveMessage(mensagem);
        }catch (IllegalArgumentException e){
            System.out.println(e);
        }

    }

    @Transactional
    private void saveMessage(Mensagem mensagem) {
        mensagemRepository.save(mensagem);
    }
}
