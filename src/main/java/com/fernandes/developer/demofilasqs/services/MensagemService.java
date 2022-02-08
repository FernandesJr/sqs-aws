package com.fernandes.developer.demofilasqs.services;

import com.fernandes.developer.demofilasqs.dto.MensagemDTO;
import com.fernandes.developer.demofilasqs.entities.Mensagem;
import com.fernandes.developer.demofilasqs.repositories.MensagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MensagemService {

    @Autowired
    private MensagemRepository repository;

    //Busca paginada
    public List<MensagemDTO> findMensagensPaged(Pageable pageable){
        Page<Mensagem> mensagens = repository.findAll(pageable);

        List<MensagemDTO> list = new ArrayList<>();
        mensagens.map(m -> list.add(new MensagemDTO(m)));

        return list;
    }
}
