package com.fernandes.developer.demofilasqs.dto;

import java.io.Serializable;

public class MensagemDTO implements Serializable {

    private Long id;
    private String titulo;
    private String texto;

    public MensagemDTO(){}

    public MensagemDTO(String titulo, String texto) {
        this.titulo = titulo;
        this.texto = texto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String msgJson(){
        return "{\"titulo\":\""+this.titulo+"\",\"texto\":\""+texto+"\"}";
    }
}
