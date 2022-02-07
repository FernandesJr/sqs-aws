package com.fernandes.developer.demofilasqs.entities;

import com.fernandes.developer.demofilasqs.dto.MensagemDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_mensagem")
public class Mensagem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String texto;

    public Mensagem(){}

    public Mensagem(String titulo, String texto) {
        this.titulo = titulo;
        this.texto = texto;
    }

    public Mensagem(MensagemDTO dto) {
        this.titulo = dto.getTitulo();
        this.texto = dto.getTexto();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mensagem)) return false;
        Mensagem mensagem = (Mensagem) o;
        return Objects.equals(getId(), mensagem.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public boolean IsEmpty() {
        return this.titulo == null;
    }

    public void setEmpty(){
        this.titulo = null;
    }
}
