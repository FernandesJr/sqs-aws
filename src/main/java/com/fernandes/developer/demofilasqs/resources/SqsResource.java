package com.fernandes.developer.demofilasqs.resources;

import com.fernandes.developer.demofilasqs.dto.MensagemDTO;
import com.fernandes.developer.demofilasqs.services.SqsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sqs")
public class SqsResource {

    @Autowired
    private SqsService service;

    @PostMapping
    public ResponseEntity<Void> sendToQueue(@RequestBody MensagemDTO mensagemDTO){
        service.sendMensagemToQueue(mensagemDTO);
        return ResponseEntity.ok().build();
    }

}
