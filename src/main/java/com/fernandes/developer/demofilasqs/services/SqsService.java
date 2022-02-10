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

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Service
public class SqsService implements ActionListener {

    public SqsService(){
        this.timeToSearch();
    }

    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;

    @Value("${cloud.aws.end-point.uri}")
    private String endPoint;

    @Autowired
    private MensagemRepository mensagemRepository;

    private Integer TIME_TO_SEARCH_QUEUE = 300000; //5 min == 300000 ms

    public void sendMensagemToQueue(MensagemDTO dto){
        this.queueMessagingTemplate.send(endPoint, MessageBuilder.withPayload(dto.msgToJson()).build());
    }

    public void listenQueue(){
        try{
            System.out.println("LISTEN-QUEUE");
            //Buscar as mensagens da fila sqs
            Mensagem mensagem = this.queueMessagingTemplate.receiveAndConvert(endPoint, Mensagem.class);
            if(!mensagem.IsEmpty()){
                this.saveMessage(mensagem);
            }
            while (!mensagem.IsEmpty()){
                Mensagem mensagemSubSequentes = this.queueMessagingTemplate.receiveAndConvert(endPoint, Mensagem.class);
                if(mensagemSubSequentes.IsEmpty()){
                    mensagem.setEmpty();
                }else{
                    this.saveMessage(mensagemSubSequentes);
                }
            }
        }catch (NullPointerException e){
            System.out.println("Fila vazia: "+e);
        }
    }

    @Transactional
    private void saveMessage(Mensagem mensagem) {
        this.mensagemRepository.save(mensagem);
    }

    public void timeToSearch(){
        Timer time = new Timer(TIME_TO_SEARCH_QUEUE, this); //1K = 1s
        time.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.listenQueue();
    }

    //Buscar as mensagens de forma automática
    /*
    @SqsListener(value = "filaTest", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void listenQueue(String message){
        try{
            //A mensagem foi salva em formato Json, sendo assim aqui converte-se para objeto java
            Mensagem mensagem = new Gson().fromJson(message, Mensagem.class);
            //Salvando no banco de dados
            saveMessage(mensagem);
        }catch (IllegalArgumentException e){
            System.out.println(e);
        }
    }*/
}
