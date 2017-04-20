package com.example.rafael.linschat.domain;

/**
 * Jovem Soluções M.E.
 * Created by Rafael  on 17/04/2017.
 * Todos os direitos reservados.
 */

public class Mensagem {
    private String nomeUsuario;
    private String message;

    public Mensagem() {
    }

    public Mensagem(String nomeUsuario, String message) {
        this.nomeUsuario = nomeUsuario;
        this.message = message;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
