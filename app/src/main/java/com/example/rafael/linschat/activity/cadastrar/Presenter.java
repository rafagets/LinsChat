package com.example.rafael.linschat.activity.cadastrar;

import com.example.rafael.linschat.domain.Usuario;

/**
 * Jovem Soluções M.E.
 * Created by Rafael  on 18/04/2017.
 * Todos os direitos reservados.
 */

public class Presenter implements MVPCadastro.Presenter {
    private MVPCadastro.View view;
    private MVPCadastro.Model model;

    public Presenter() {
        this.model = new Model(this);
    }

    @Override
    public void cadastrar(String nome, String email, String senha) {
        model.cadastrar(new Usuario(nome, email, senha));
    }

    @Override
    public void setView(MVPCadastro.View view) {
        this.view = view;
    }

    @Override
    public void fechar() {
        view.fechar();
    }

    @Override
    public void setDialog(String message){
        view.setDialog(message);
    }

    @Override
    public void closeDialog(){
        view.closeDialog();
    }

    @Override
    public void showMessage(String message) {
        view.fechar();
    }
}
