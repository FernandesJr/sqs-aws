package com.fernandes.developer.demofilasqs.resources;

import com.fernandes.developer.demofilasqs.dto.MensagemDTO;
import com.fernandes.developer.demofilasqs.services.MensagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mensagens")
public class MensagemResource {

    @Autowired
    private MensagemService service;

    @GetMapping
    public ResponseEntity<List<MensagemDTO>> findMensagens(Pageable pageable){
        return ResponseEntity.ok().body(service.findMensagensPaged(pageable));
    }
}
