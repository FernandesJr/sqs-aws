package com.fernandes.developer.demofilasqs.repositories;

import com.fernandes.developer.demofilasqs.entities.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MensagemRepository extends JpaRepository<Mensagem, Long> {
}
